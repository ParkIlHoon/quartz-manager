package dev.hoon.quartzmanager.core.aop

import dev.hoon.quartzmanager.batch.BatchDataSourceRouter
import dev.hoon.quartzmanager.core.domain.service.WorkspaceService
import dev.hoon.quartzmanager.batch.BatchDataSourceStore
import dev.hoon.quartzmanager.batch.BatchPropertyRouter
import dev.hoon.quartzmanager.core.DataSourceWrapper
import dev.hoon.quartzmanager.core.utils.DBConnectionTester
import dev.hoon.quartzmanager.quartz.QuartzDataSourceRouter
import dev.hoon.quartzmanager.quartz.QuartzDataSourceStore
import dev.hoon.quartzmanager.quartz.QuartzPropertyRouter
import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Aspect
@Component
class UpdateDataSourceAspect(
    private val workspaceService: WorkspaceService,
    private val batchDataSourceRouter: BatchDataSourceRouter,
    private val batchPropertyRouter: BatchPropertyRouter,
    private val quartzDataSourceRouter: QuartzDataSourceRouter,
    private val quartzPropertyRouter: QuartzPropertyRouter,
) {

    @Around("@annotation(dev.hoon.quartzmanager.core.annotation.UpdateDataSource)")
    fun update(joinPoint: ProceedingJoinPoint): Any? {
        val proceed = joinPoint.proceed()

        logger.info { "update datasource..." }

        workspaceService.getAllWorkspaces().forEach {
            it.batchConnection?.let {
                ci -> run {
                    if (DBConnectionTester.test(ci)) {
                        BatchDataSourceStore.add(DataSourceWrapper.of(it.serviceKey, ci))
                        batchPropertyRouter.setTablePrefix(it.serviceKey, ci.tablePrefix)
                        logger.info { "[${it.serviceKey}] batch datasource has been successfully initialized." }
                    }
                }
            }

            it.quartzConnection?.let {
                ci -> run {
                    if (DBConnectionTester.test(ci)) {
                        QuartzDataSourceStore.add(DataSourceWrapper.of(it.serviceKey, ci))
                        quartzPropertyRouter.setTablePrefix(it.serviceKey, ci.tablePrefix)
                        logger.info { "[${it.serviceKey}] quartz datasource has been successfully initialized." }
                    }
                }
            }
        }

        batchDataSourceRouter.setTargetDataSources(BatchDataSourceStore.getAll())
        batchDataSourceRouter.afterPropertiesSet()

        quartzDataSourceRouter.setTargetDataSources(QuartzDataSourceStore.getAll())
        quartzDataSourceRouter.afterPropertiesSet()

        logger.info { "datasources updated!" }

        return proceed
    }

}
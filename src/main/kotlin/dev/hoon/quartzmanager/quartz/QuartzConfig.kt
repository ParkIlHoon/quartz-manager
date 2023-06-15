package dev.hoon.quartzmanager.quartz

import dev.hoon.quartzmanager.core.configuration.CoreDataSourceConfig
import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.quartz.QuartzDataSource
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import java.util.*
import javax.sql.DataSource

@Configuration
class QuartzConfig(
    private val applicationContext: ApplicationContext,
    private val quartzPropertyRouter: QuartzPropertyRouter,
    @Qualifier(CoreDataSourceConfig.DATA_SOURCE) private val coreDataSource: DataSource,
) {

    @Bean
    @QuartzDataSource
    fun quartzDataSourceByWorkspace(): QuartzDataSourceRouter {
        val quartzDataSourceRouter = QuartzDataSourceRouter()
        quartzDataSourceRouter.setTargetDataSources(mapOf("core" to coreDataSource))
        return quartzDataSourceRouter
    }

    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean {
        val schedulerFactoryBean = SchedulerFactoryBean()
        val jobFactory = AutowiringSpringBeanJobFactory()
        jobFactory.setApplicationContext(applicationContext)
        schedulerFactoryBean.setJobFactory(jobFactory)
        schedulerFactoryBean.setApplicationContext(applicationContext)
        schedulerFactoryBean.setOverwriteExistingJobs(true)
        schedulerFactoryBean.setQuartzProperties(quartzPropertyRouter.getAsProperties(WorkspaceContextHolder.get().serviceKey))
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true)
        schedulerFactoryBean.setDataSource(quartzDataSourceByWorkspace())
        return schedulerFactoryBean
    }

}
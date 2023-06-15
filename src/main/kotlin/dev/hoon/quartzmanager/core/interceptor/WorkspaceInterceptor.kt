package dev.hoon.quartzmanager.core.interceptor

import dev.hoon.quartzmanager.batch.BatchJobExplorerRouter
import dev.hoon.quartzmanager.batch.BatchPropertyRouter
import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import dev.hoon.quartzmanager.core.domain.service.WorkspaceService
import dev.hoon.quartzmanager.quartz.QuartzPropertyRouter
import dev.hoon.quartzmanager.quartz.QuartzSchedulerRouter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

@Component
class WorkspaceInterceptor(
    private val workspaceService: WorkspaceService,
    private val quartzPropertyRouter: QuartzPropertyRouter,
    private val quartzSchedulerRouter: QuartzSchedulerRouter,
    private val schedulerFactoryBean: SchedulerFactoryBean,
    private val batchPropertyRouter: BatchPropertyRouter,
    private val jobExplorerFactoryBean: JobExplorerFactoryBean,
    private val batchJobExplorerRouter: BatchJobExplorerRouter,
): HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val serviceKey = request.getHeader("Workspace");
        if (serviceKey.isNotBlank()) {
            val workspace = workspaceService.getWorkspace(serviceKey)
            WorkspaceContextHolder.put(workspace)
            workspace.quartzConnection?.tablePrefix?.let {
                quartzPropertyRouter.setTablePrefix(serviceKey, it)
            }

            schedulerFactoryBean.setQuartzProperties(quartzPropertyRouter.getAsProperties(WorkspaceContextHolder.get().serviceKey))
            schedulerFactoryBean.afterPropertiesSet()
            if (quartzSchedulerRouter.isEmpty(serviceKey)) {
                quartzSchedulerRouter.put(serviceKey, schedulerFactoryBean.scheduler)
            }

            jobExplorerFactoryBean.setTablePrefix(batchPropertyRouter.getTablePrefix(WorkspaceContextHolder.get().serviceKey))
            jobExplorerFactoryBean.afterPropertiesSet()
            if (batchJobExplorerRouter.isEmpty(serviceKey)) {
                batchJobExplorerRouter.put(serviceKey, jobExplorerFactoryBean.`object`)
            }
        }
        return super.preHandle(request, response, handler)
    }

    override fun postHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any, modelAndView: ModelAndView?) {
        WorkspaceContextHolder.clear()
        super.postHandle(request, response, handler, modelAndView)
    }
}
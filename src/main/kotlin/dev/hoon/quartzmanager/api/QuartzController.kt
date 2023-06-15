package dev.hoon.quartzmanager.api

import dev.hoon.quartzmanager.api.dto.QuartzJob
import dev.hoon.quartzmanager.api.dto.QuartzTrigger
import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import dev.hoon.quartzmanager.quartz.QuartzSchedulerRouter
import org.quartz.impl.matchers.GroupMatcher
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/quartz")
class QuartzController(
    private val quartzSchedulerRouter: QuartzSchedulerRouter
) {

    @GetMapping("/jobs")
    fun getAllJob(): List<QuartzJob> {
        val scheduler = quartzSchedulerRouter.get(WorkspaceContextHolder.get().serviceKey)
        return scheduler?.getJobKeys(GroupMatcher.anyJobGroup())?.map {
            QuartzJob.of(scheduler.getJobDetail(it))
        }?: listOf()
    }

    @GetMapping("/triggers")
    fun getAllTrigger(): List<QuartzTrigger> {
        val scheduler = quartzSchedulerRouter.get(WorkspaceContextHolder.get().serviceKey)
        return scheduler?.getTriggerKeys(GroupMatcher.anyGroup())?.map {
            val trigger = scheduler.getTrigger(it)
            val triggerState = scheduler.getTriggerState(it)
            QuartzTrigger.of(trigger, triggerState)
        }?: listOf()
    }

}
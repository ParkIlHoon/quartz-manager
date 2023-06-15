package dev.hoon.quartzmanager.quartz

import org.quartz.Scheduler
import org.springframework.stereotype.Component

@Component
class QuartzSchedulerRouter {

    private val schedulerMap = HashMap<String, Scheduler>()

    fun put(serviceKey: String, scheduler: Scheduler) {
        schedulerMap[serviceKey] = scheduler
    }

    fun get(serviceKey: String) = schedulerMap[serviceKey]

    fun isEmpty(serviceKey: String) = !schedulerMap.containsKey(serviceKey)
}
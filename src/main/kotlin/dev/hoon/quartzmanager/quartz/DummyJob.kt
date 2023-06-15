package dev.hoon.quartzmanager.quartz

import org.quartz.Job
import org.quartz.JobExecutionContext

class DummyJob: Job {
    override fun execute(context: JobExecutionContext?) {
        TODO("Not yet implemented")
    }
}
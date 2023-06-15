package dev.hoon.quartzmanager.api.dto

import org.quartz.JobDetail

data class QuartzJob(
    val key: QuartzKey,
    val description: String? = null,
    val isDurable: Boolean,
    val jobDataMap: Map<String, Any>
) {
    companion object {
        fun of(jobDetail: JobDetail): QuartzJob =
            QuartzJob(
                key = QuartzKey.of(jobDetail.key),
                description = jobDetail.description,
                isDurable = jobDetail.isDurable,
                jobDataMap = jobDetail.jobDataMap
            )
    }
}

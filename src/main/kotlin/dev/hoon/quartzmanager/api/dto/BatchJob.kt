package dev.hoon.quartzmanager.api.dto

import org.springframework.batch.core.JobInstance

data class BatchJob(
    val jobName: String,
    val instanceId: Long,
    val version: Int,
 ) {
    companion object {
        fun of(jobInstance: JobInstance) =
            with(jobInstance) {
                BatchJob(
                    jobName = jobName,
                    instanceId = instanceId,
                    version = version,
                )
            }
    }
}

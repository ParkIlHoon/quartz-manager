package dev.hoon.quartzmanager.api.dto

import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.JobExecution
import java.time.LocalDateTime

data class BatchJobExecution(
    val jobExecutionId: Long,
    val jobInstance: BatchJob,
    val jobParameters: Map<String, Any>,
    val status: BatchStatus,
    val startTime: LocalDateTime? = null,
    val createTime: LocalDateTime,
    val endTime: LocalDateTime? = null,
    val lastUpdated: LocalDateTime? = null,
    val exitStatus: ExitStatus,
) {
    companion object {
        fun of(jobExecution: JobExecution) =
            with(jobExecution) {
                BatchJobExecution(
                    jobExecutionId = id,
                    jobInstance = BatchJob.of(jobInstance),
                    jobParameters = jobParameters.parameters.toMap(),
                    status = status,
                    startTime = startTime,
                    createTime = createTime,
                    endTime = endTime,
                    lastUpdated = lastUpdated,
                    exitStatus = exitStatus
                )
            }
    }
}
package dev.hoon.quartzmanager.api.dto

import org.springframework.batch.core.BatchStatus
import org.springframework.batch.core.ExitStatus
import org.springframework.batch.core.StepExecution
import java.time.LocalDateTime

data class BatchStepExecution(
    val jobExecution: BatchJobExecution,
    val stepName: String,
    val status: BatchStatus,
    val readCount: Long,
    val writeCount: Long,
    val commitCount: Long,
    val rollbackCount: Long,
    val readSkipCount: Long,
    val processSkipCount: Long,
    val writeSkipCount: Long,
    val startTime: LocalDateTime? = null,
    val createTime: LocalDateTime,
    val endTime: LocalDateTime? = null,
    val lastUpdated: LocalDateTime? = null,
    val exitStatus: ExitStatus,
    val filterCount: Long,
) {
    companion object {
        fun of(stepExecution: StepExecution) =
            with(stepExecution) {
                BatchStepExecution(
                    jobExecution = BatchJobExecution.of(jobExecution),
                    stepName = stepName,
                    status = status,
                    readCount = readCount,
                    writeCount = writeCount,
                    commitCount = commitCount,
                    rollbackCount = rollbackCount,
                    readSkipCount = readSkipCount,
                    processSkipCount = processSkipCount,
                    writeSkipCount = writeSkipCount,
                    startTime = startTime,
                    createTime = createTime,
                    endTime = endTime,
                    lastUpdated = lastUpdated,
                    exitStatus = exitStatus,
                    filterCount = filterCount
                )
            }
    }
}
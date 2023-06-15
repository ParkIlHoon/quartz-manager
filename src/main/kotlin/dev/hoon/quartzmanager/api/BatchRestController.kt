package dev.hoon.quartzmanager.api

import dev.hoon.quartzmanager.api.dto.BatchJob
import dev.hoon.quartzmanager.api.dto.BatchJobExecution
import dev.hoon.quartzmanager.api.dto.BatchStepExecution
import dev.hoon.quartzmanager.batch.BatchJobExplorerRouter
import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/batch")
class BatchRestController(
    private val batchJobExplorerRouter: BatchJobExplorerRouter,
) {

    @GetMapping("/jobs")
    fun getJobList(): List<BatchJob> {
        val jobExplorer = batchJobExplorerRouter.get(WorkspaceContextHolder.get().serviceKey)
        return jobExplorer?.jobNames?.mapNotNull {
            jobExplorer.getLastJobInstance(it)
        }?.map {
            BatchJob.of(it)
        }?: listOf()
    }

    @GetMapping("/jobs/{jobInstanceId}/executions")
    fun getJobExecutions(@PathVariable("jobInstanceId") jobInstanceId: Long): List<BatchJobExecution> {
        val jobExplorer = batchJobExplorerRouter.get(WorkspaceContextHolder.get().serviceKey)
        val jobInstance = jobExplorer?.getJobInstance(jobInstanceId)
        return jobInstance?.let {
            jobExplorer.getJobExecutions(it)
        }?.map {
            BatchJobExecution.of(it)
        }?: listOf()
    }

    @GetMapping("/jobs/{jobInstanceId}/executions/{jobExecutionId}/steps")
    fun getStepExecutions(
        @PathVariable("jobInstanceId") jobInstanceId: Long,
        @PathVariable("jobExecutionId") jobExecutionId: Long
    ): List<BatchStepExecution> {
        val jobExplorer = batchJobExplorerRouter.get(WorkspaceContextHolder.get().serviceKey)
        val jobExecution = jobExplorer?.getJobExecution(jobExecutionId)
        return jobExecution?.stepExecutions
            ?.map {
                BatchStepExecution.of(it)
            }?: listOf()
    }
}
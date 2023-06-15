package dev.hoon.quartzmanager.api

import dev.hoon.quartzmanager.api.dto.BatchJob
import dev.hoon.quartzmanager.batch.BatchJobExplorerRouter
import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import org.springframework.batch.core.explore.JobExplorer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/batch")
class BatchController(
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

    @GetMapping("/jobs/{jobInstanceId}")
    fun getJobExecutions(@PathVariable("jobInstanceId") jobInstanceId: Long) {
        val jobExplorer = batchJobExplorerRouter.get(WorkspaceContextHolder.get().serviceKey)
        val jobInstance = jobExplorer?.getJobInstance(jobInstanceId)
        jobInstance?.let {
            jobExplorer.getJobExecutions(it)
        }
    }
}
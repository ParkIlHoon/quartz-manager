package dev.hoon.quartzmanager.batch

import org.springframework.batch.core.explore.JobExplorer
import org.springframework.stereotype.Component

@Component
class BatchJobExplorerRouter {

    private val jobExplorerMap = HashMap<String, JobExplorer>()

    fun put(serviceKey: String, jobExplorer: JobExplorer) {
        jobExplorerMap[serviceKey] = jobExplorer
    }

    fun get(serviceKey: String) = jobExplorerMap[serviceKey]

    fun isEmpty(serviceKey: String) = !jobExplorerMap.containsKey(serviceKey)

}
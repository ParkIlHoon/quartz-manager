package dev.hoon.quartzmanager.batch

import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource


class BatchDataSourceRouter: AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any = WorkspaceContextHolder.get().serviceKey
}
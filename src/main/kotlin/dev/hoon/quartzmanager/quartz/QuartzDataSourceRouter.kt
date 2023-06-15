package dev.hoon.quartzmanager.quartz

import dev.hoon.quartzmanager.core.context.WorkspaceContextHolder
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource

class QuartzDataSourceRouter: AbstractRoutingDataSource() {
    override fun determineCurrentLookupKey(): Any = WorkspaceContextHolder.get().serviceKey
}
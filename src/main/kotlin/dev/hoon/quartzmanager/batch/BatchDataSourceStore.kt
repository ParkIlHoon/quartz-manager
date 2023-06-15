package dev.hoon.quartzmanager.batch

import dev.hoon.quartzmanager.core.DataSourceWrapper
import javax.sql.DataSource

/**
 * 워크스페이스 당 2개의 DataSource를 가질 수 있음 (Spring Batch, Quartz)
 */
class BatchDataSourceStore {
    companion object {
        private val dataSourceMap = HashMap<String, DataSource>()


        fun add(dataSourceWrapper: DataSourceWrapper) {
            dataSourceMap[dataSourceWrapper.key] = dataSourceWrapper.dataSource
        }

        fun addAll(dataSourceWrappers: List<DataSourceWrapper>) {
            dataSourceWrappers.forEach {
                dataSourceMap[it.key] = it.dataSource
            }
        }

        fun getAll(): Map<Any, Any> = dataSourceMap.toMap()
    }
}
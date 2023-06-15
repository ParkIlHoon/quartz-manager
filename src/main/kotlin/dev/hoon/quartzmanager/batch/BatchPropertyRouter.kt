package dev.hoon.quartzmanager.batch

import org.springframework.batch.core.repository.dao.AbstractJdbcBatchMetadataDao
import org.springframework.stereotype.Component

@Component
class BatchPropertyRouter {

    private val propertyMap = HashMap<String, HashMap<String, Any>>()

    fun put(serviceKey: String, properties: HashMap<String, Any>) {
        propertyMap[serviceKey] = properties
    }

    fun get(serviceKey: String?) = propertyMap[serviceKey]

    fun getTablePrefix(serviceKey: String) =
        propertyMap[serviceKey]?.getOrDefault("tablePrefix", AbstractJdbcBatchMetadataDao.DEFAULT_TABLE_PREFIX).toString()

    fun setTablePrefix(serviceKey: String, tablePrefix: String) {
        if (propertyMap[serviceKey] == null) {
            propertyMap[serviceKey] = HashMap()
        }

        propertyMap[serviceKey]?.set("tablePrefix", tablePrefix)
    }
}
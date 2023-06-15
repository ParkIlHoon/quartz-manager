package dev.hoon.quartzmanager.quartz

import org.springframework.boot.autoconfigure.quartz.QuartzProperties
import org.springframework.stereotype.Component
import java.util.Properties

@Component
class QuartzPropertyRouter(
    private val quartzProperties: QuartzProperties,
) {

    private val propertyMap = HashMap<String, HashMap<String, Any>>()

    fun put(serviceKey: String, properties: HashMap<String, Any>) {
        propertyMap[serviceKey] = properties
    }

    fun get(serviceKey: String?) = propertyMap[serviceKey]

    fun getAsProperties(serviceKey: String): Properties {
        val properties = Properties()
        properties.putAll(quartzProperties.properties)
        properties["org.quartz.jobStore.tablePrefix"] = getTablePrefix(serviceKey)?: "QRTZ_"
        return properties
    }

    fun getTablePrefix(serviceKey: String) = propertyMap[serviceKey]?.getOrDefault("tablePrefix", "QRTZ_")

    fun setTablePrefix(serviceKey: String, tablePrefix: String) {
        if (propertyMap[serviceKey] == null) {
            propertyMap[serviceKey] = HashMap()
        }

        propertyMap[serviceKey]?.set("tablePrefix", tablePrefix)
    }
}
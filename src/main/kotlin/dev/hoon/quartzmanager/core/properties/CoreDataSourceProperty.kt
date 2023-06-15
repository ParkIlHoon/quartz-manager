package dev.hoon.quartzmanager.core.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "application.core.datasource")
data class CoreDataSourceProperty(
    val serviceKey:String = "core",
    val url: String,
    val username: String,
    val password: String,
    val driverClassName: String,
)
package dev.hoon.quartzmanager.core

import com.zaxxer.hikari.HikariDataSource
import dev.hoon.quartzmanager.core.domain.ConnectionInfo
import org.springframework.boot.jdbc.DataSourceBuilder
import javax.sql.DataSource

class DataSourceWrapper(
    val key: String,
    val tablePrefix: String,
    val dataSource: DataSource
) {
    companion object {
        fun of(serviceKey: String, connectionInfo: ConnectionInfo) =
            DataSourceWrapper(
                key = serviceKey,
                tablePrefix = connectionInfo.tablePrefix,
                dataSource = DataSourceBuilder.create()
                    .url(connectionInfo.url)
                    .username(connectionInfo.username)
                    .password(connectionInfo.password)
                    .driverClassName(connectionInfo.driverClassName)
                    .build()
            )
    }
}
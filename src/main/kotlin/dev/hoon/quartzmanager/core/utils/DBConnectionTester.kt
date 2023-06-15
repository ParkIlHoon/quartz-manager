package dev.hoon.quartzmanager.core.utils

import dev.hoon.quartzmanager.core.domain.ConnectionInfo
import java.sql.DriverManager

class DBConnectionTester {
    companion object {

        private const val CONNECTION_TIMEOUT = "&connectTimeout=1000"

        fun test(connectionInfo: ConnectionInfo): Boolean {
            val connection = DriverManager.getConnection(connectionInfo.url + CONNECTION_TIMEOUT, connectionInfo.username, connectionInfo.password)
            return connection.use {
                connection.isValid(1)
            }
        }
    }
}
package dev.hoon.quartzmanager.core.domain

data class ConnectionInfo(
    var url: String,
    var username: String,
    var password: String,
    var driverClassName: String,
    var tablePrefix: String,
)

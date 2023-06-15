package dev.hoon.quartzmanager.core.context

import dev.hoon.quartzmanager.core.domain.ConnectionInfo

data class WorkspaceContext(
    val serviceKey: String,
    var name: String,
    val quartzConnection: ConnectionInfo? = null,
    var batchConnection: ConnectionInfo? = null
)

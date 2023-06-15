package dev.hoon.quartzmanager.core.context

import dev.hoon.quartzmanager.core.domain.entity.Workspace

class WorkspaceContextHolder {
    companion object {
        private val threadLocal: ThreadLocal<WorkspaceContext> = ThreadLocal()

        fun put(workspace: Workspace) = threadLocal.set(
            WorkspaceContext(
                serviceKey = workspace.serviceKey,
                name = workspace.name,
                quartzConnection = workspace.quartzConnection,
                batchConnection = workspace.batchConnection,
            )
        )

        fun get(): WorkspaceContext = threadLocal.get()?: WorkspaceContext(serviceKey = "core", name = "core")

        fun clear() = threadLocal.remove()
    }
}
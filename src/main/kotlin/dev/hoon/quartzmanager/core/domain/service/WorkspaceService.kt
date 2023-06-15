package dev.hoon.quartzmanager.core.domain.service

import dev.hoon.quartzmanager.core.domain.repository.WorkspaceRepository
import org.springframework.stereotype.Service

@Service
class WorkspaceService(
    private val workspaceRepository: WorkspaceRepository
) {

    fun getAllWorkspaces() = workspaceRepository.findAll()

    fun getWorkspace(serviceKey: String) = workspaceRepository.findById(serviceKey).orElseThrow()

}
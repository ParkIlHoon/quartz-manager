package dev.hoon.quartzmanager.core.domain.repository

import dev.hoon.quartzmanager.core.domain.entity.Workspace
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WorkspaceRepository: JpaRepository<Workspace, String> {
}
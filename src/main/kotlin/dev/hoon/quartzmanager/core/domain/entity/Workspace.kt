package dev.hoon.quartzmanager.core.domain.entity

import dev.hoon.quartzmanager.core.domain.ConnectionInfo
import dev.hoon.quartzmanager.core.domain.converter.ConnectionInfoConverter
import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Table(name = "workspace")
@Entity
class Workspace(
    serviceKey: String,
    name: String,
    quartzConnectionInfo: ConnectionInfo?,
    batchConnectionInfo: ConnectionInfo?
) {
    @Id
    @Column(name = "service_key")
    val serviceKey: String = serviceKey

    @Column(name = "name", nullable = false)
    var name: String = name

    @Column(name = "quartz_connection", length = 1000)
    @Convert(converter = ConnectionInfoConverter::class)
    var quartzConnection: ConnectionInfo? = quartzConnectionInfo

    @Column(name = "batch_connection", length = 1000)
    @Convert(converter = ConnectionInfoConverter::class)
    var batchConnection: ConnectionInfo? = batchConnectionInfo

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "updated_at")
    @LastModifiedDate
    var updatedAt: LocalDateTime? = null
}
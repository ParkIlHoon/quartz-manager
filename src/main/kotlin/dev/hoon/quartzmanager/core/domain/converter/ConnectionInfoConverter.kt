package dev.hoon.quartzmanager.core.domain.converter

import com.google.gson.Gson
import dev.hoon.quartzmanager.core.domain.ConnectionInfo
import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter
import org.springframework.stereotype.Component

@Converter
@Component
class ConnectionInfoConverter: AttributeConverter<ConnectionInfo, String> {

    override fun convertToDatabaseColumn(attribute: ConnectionInfo?): String = Gson().toJson(attribute)

    override fun convertToEntityAttribute(dbData: String?): ConnectionInfo = Gson().fromJson(dbData, ConnectionInfo::class.java)
}
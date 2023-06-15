package dev.hoon.quartzmanager.core

import dev.hoon.quartzmanager.core.annotation.UpdateDataSource
import mu.KotlinLogging
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger {}

@Component
class DataSourceInitializer: ApplicationRunner {
    @UpdateDataSource
    override fun run(args: ApplicationArguments?) {
        logger.info { "DataSourceInitializer started." }
    }
}
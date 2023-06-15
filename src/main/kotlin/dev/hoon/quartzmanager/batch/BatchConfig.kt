package dev.hoon.quartzmanager.batch

import dev.hoon.quartzmanager.core.configuration.CoreDataSourceConfig
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@EnableBatchProcessing(
    dataSourceRef = BatchConfig.DATA_SOURCE_REF,
    transactionManagerRef = BatchConfig.TRANSACTION_MANAGER_REF
)
@Configuration
class BatchConfig(
    @Qualifier(CoreDataSourceConfig.DATA_SOURCE) private val coreDataSource: DataSource,
) {
    companion object {
        const val DATA_SOURCE_REF = "batchDataSourceRouter"
        const val TRANSACTION_MANAGER_REF = "batchTransactionManager"
    }

    @Bean(name = [DATA_SOURCE_REF])
    fun batchDataSourceByWorkspace(): BatchDataSourceRouter {
        val batchDataSourceRouter = BatchDataSourceRouter()
        batchDataSourceRouter.setTargetDataSources(mapOf("core" to coreDataSource))
        return batchDataSourceRouter
    }

    @Bean(name = [TRANSACTION_MANAGER_REF])
    fun batchTransactionManager(): PlatformTransactionManager = DataSourceTransactionManager(batchDataSourceByWorkspace())
}
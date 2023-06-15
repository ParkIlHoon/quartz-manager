package dev.hoon.quartzmanager.core.configuration

import dev.hoon.quartzmanager.core.properties.CoreDataSourceProperty
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.sql.DataSource

@EnableJpaRepositories(
    basePackages = [CoreDataSourceConfig.BASE_PACKAGE],
    entityManagerFactoryRef = CoreDataSourceConfig.ENTITY_MANAGER,
    transactionManagerRef = CoreDataSourceConfig.TRANSACTION_MANAGER
)
@Configuration
class CoreDataSourceConfig(
    private val coreDataSourceProperty: CoreDataSourceProperty
) {
    companion object {
        const val BASE_PACKAGE = "dev.hoon.quartzmanager.core"
        const val DATA_SOURCE = "coreDataSource"
        const val ENTITY_MANAGER = "coreEntityManager"
        const val TRANSACTION_MANAGER = "coreTransactionManager"
    }

    @Primary
    @Bean(name = [DATA_SOURCE])
    fun dataSource(): DataSource =
        with(coreDataSourceProperty) {
            DataSourceBuilder.create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build()
        }

    @Bean(name = [ENTITY_MANAGER])
    fun entityManagerFactoryBean(builder: EntityManagerFactoryBuilder): LocalContainerEntityManagerFactoryBean =
        builder.dataSource(dataSource())
            .packages(BASE_PACKAGE)
            .build()

    @Bean(name = [TRANSACTION_MANAGER])
    fun transactionManager(builder: EntityManagerFactoryBuilder) =
        entityManagerFactoryBean(builder).`object`?.let { JpaTransactionManager(it) }
}
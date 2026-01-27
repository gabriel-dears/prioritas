package br.com.fiap.adj8.phase5.prioritas

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
abstract class AbstractIntegrationTest {

    companion object {
        // Define o container do Postgres especificamente para o teste
        @Container
        val postgresContainer = PostgreSQLContainer("postgres:16-alpine")
            .withDatabaseName("prioritas-test")
            .withUsername("test")
            .withPassword("test")

        // Injeta as propriedades do container dinâmico no application.properties do Spring
        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" } // Recria o banco a cada teste
        }
    }
}
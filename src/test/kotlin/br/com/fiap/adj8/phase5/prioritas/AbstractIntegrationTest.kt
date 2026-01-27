package br.com.fiap.adj8.phase5.prioritas

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
// @Testcontainers <- REMOVIDO: Vamos controlar manualmente para evitar race condition
abstract class AbstractIntegrationTest {

    companion object {
        // Definimos o container como uma variável estática normal
        val postgresContainer = PostgreSQLContainer("postgres:16-alpine")
            .withDatabaseName("prioritas-test")
            .withUsername("test")
            .withPassword("test")

        // Bloco de inicialização estático: Roda ANTES de qualquer coisa do Spring
        init {
            postgresContainer.start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", postgresContainer::getJdbcUrl)
            registry.add("spring.datasource.username", postgresContainer::getUsername)
            registry.add("spring.datasource.password", postgresContainer::getPassword)
            // Mantemos create-drop para limpar o banco entre contextos
            registry.add("spring.jpa.hibernate.ddl-auto") { "create-drop" }
        }
    }
}
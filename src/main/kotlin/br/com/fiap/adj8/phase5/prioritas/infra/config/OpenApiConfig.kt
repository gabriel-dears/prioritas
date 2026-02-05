package br.com.fiap.adj8.phase5.prioritas.infra.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@OpenAPIDefinition(
    info = io.swagger.v3.oas.annotations.info.Info(title = "Prioritas API", version = "v1"),
    security = [SecurityRequirement(name = "basicAuth")]
)
@SecurityScheme(
    name = "basicAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "basic"
)
class OpenApiConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Prioritas API - Sistema de Triagem")
                    .version("1.0.0")
                    .description("API responsável pela classificação de risco de pacientes em emergências hospitalares.")
                    .contact(Contact().name("Gabriel").email("gabriel@fiap.com.br"))
            )
    }
}
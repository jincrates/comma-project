package me.jincrates.comma.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    private val securitySchemeKey = "bearerAuth"

    @Bean
    fun openAPI(): OpenAPI {
        return OpenAPI()
                .info(info())
                .components(securityScheme())
                .security(securityList())
    }

    private fun info(): Info {
        return Info()
                .title("Comma Project")
                .description("API")
                .version("1.0")
    }

    private fun securityScheme(): Components {
        val securityScheme = SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer").bearerFormat("JWT")
                .`in`(SecurityScheme.In.HEADER).name("Authorization")

        return Components().addSecuritySchemes(securitySchemeKey, securityScheme)
    }

    private fun securityList(): List<SecurityRequirement> {
        return listOf(SecurityRequirement().addList(securitySchemeKey))
    }
}
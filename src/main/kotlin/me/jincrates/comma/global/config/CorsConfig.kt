package me.jincrates.comma.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfig : WebMvcConfigurer {

    companion object {
        private const val MAX_AGE_SECS = 3600L
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("*")
                //.allowedOrigins(
                //    "http://localhost:3000",
                //)
                .allowedOriginPatterns("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS)
    }
}
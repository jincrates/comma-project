package me.jincrates.comma.global.config

import me.jincrates.comma.global.authority.JwtAuthenticationFilter
import me.jincrates.comma.global.authority.JwtProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig(
        private val jwtProvider: JwtProvider
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
                .httpBasic { it.disable() }
                .csrf { it.disable() }
                .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
                .authorizeHttpRequests {
                    it.requestMatchers(
                            "/api/v1/members/signup"
                    )
                            .anonymous()
                            .anyRequest()
                            .permitAll()
                }
                .addFilterBefore(
                        JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter::class.java
                )

        return http.build()
    }

    @Bean
    fun passwordEncode(): PasswordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder()
}
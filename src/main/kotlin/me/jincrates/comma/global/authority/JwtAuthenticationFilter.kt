package me.jincrates.comma.global.authority

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpHeaders
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean

class JwtAuthenticationFilter(
        private val jwtProvider: JwtProvider
) : GenericFilterBean() {
    override fun doFilter(
            request: ServletRequest?,
            response: ServletResponse?,
            chain: FilterChain?
    ) {
        val token = resolveToken(request as HttpServletRequest)

        if (token != null && jwtProvider.validateToken(token)) {
            val authentication = jwtProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        
        chain?.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        return request.getHeader(HttpHeaders.AUTHORIZATION)?.takeIf {
            it.isNotBlank() && it.startsWith("Bearer ")
        }?.substring(7)
    }
}
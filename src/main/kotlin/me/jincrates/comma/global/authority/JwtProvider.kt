package me.jincrates.comma.global.authority

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtProvider {
    @Value("\${jwt.secret}")
    lateinit var secretKey: String
    val ACCESS_TOKEN_EXPRIATION: Long = 1000 * 60 * 60 * 24 // 1day
    val REFRESH_TOKEN_EXPIRATION: Long = 1000 * 60 * 60 * 24 * 7 // 7day

    private val key by lazy {
        Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey))
    }

    /**
     * Token 생성
     */
    fun createToken(authentication: Authentication): TokenInfo {
        val authorities: String = authentication
                .authorities
                .joinToString(",", transform = GrantedAuthority::getAuthority)

        val now = Date()
        val accessExpiation = Date(now.time + ACCESS_TOKEN_EXPRIATION)
        val refreshExpiation = Date(now.time + REFRESH_TOKEN_EXPIRATION)

        val accessToken = generateToken(authentication, authorities, now, accessExpiation)
        val refreshToken = generateToken(authentication, authorities, now, refreshExpiation)

        return TokenInfo("Bearer", accessToken, refreshToken)
    }

    /**
     * Token 정보 추출
     */
    fun getAuthentication(token: String): Authentication {
        val claims: Claims = getClaims(token)
        val auth = claims["auth"] ?: throw JwtException("잘못된 토큰입니다.")

        // 권한 정보 추출
        val authorities: Collection<GrantedAuthority> = (auth as String)
                .split(",")
                .map { SimpleGrantedAuthority(it) }

        val principal: UserDetails = User(claims.subject, "", authorities)

        return UsernamePasswordAuthenticationToken(principal, "", authorities)
    }

    /**
     * Token 검증
     */
    fun validateToken(token: String): Boolean {
        try {
            getClaims(token)
            return true
        } catch (e: Exception) {
            when (e) {
                is SecurityException -> {} // Invalid JWT Token
                is MalformedJwtException -> {} // Invalid JWT Token
                is ExpiredJwtException -> {} // Expired JWT Token
                is UnsupportedJwtException -> {} // Unsupported JWT Token
                is IllegalArgumentException -> {} // JWT claims string is empty
                else -> {} // else
            }
            // logging println(e.message)
        }
        return false
    }

    private fun getClaims(token: String): Claims =
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .body

    private fun generateToken(
            authentication: Authentication,
            claim: String,
            issuedAt: Date,
            expiation: Date
    ): String =
            Jwts.builder()
                    .setSubject(authentication.name)
                    .claim("auth", claim)
                    .setIssuedAt(issuedAt)
                    .setExpiration(expiation)
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact()
}
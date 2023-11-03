package me.jincrates.comma.global.authority

data class TokenInfo(
        val grantType: String,
        val accessToken: String,
        val refreshToken: String,
)
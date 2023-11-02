package me.jincrates.comma.domain.exception

class MemberDomainException(
        message: String,
        cause: Throwable? = null
) : DomainException(message, cause)
package me.jincrates.comma.domain.exception

class MemberNotFoundException(
        message: String,
        cause: Throwable? = null
) : DomainException(message, cause)

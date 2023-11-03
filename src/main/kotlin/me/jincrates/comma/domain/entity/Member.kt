package me.jincrates.comma.domain.entity

import me.jincrates.comma.domain.valueobject.MemberId
import java.time.LocalDateTime

class Member(
        memberId: MemberId?,
        val name: String,
        val email: String,
        val password: String,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?,
) : AggregateRoot<MemberId>(memberId)
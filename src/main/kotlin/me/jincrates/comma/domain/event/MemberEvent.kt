package me.jincrates.comma.domain.event

import me.jincrates.comma.domain.entity.Member
import java.time.LocalDateTime

abstract class MemberEvent(
        val member: Member,
        val createdAt: LocalDateTime
) : DomainEvent<Member>
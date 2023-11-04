package me.jincrates.comma.domain.entity

import me.jincrates.comma.domain.status.Role
import me.jincrates.comma.domain.valueobject.MemberId
import me.jincrates.comma.domain.valueobject.MemberRoleId
import java.time.LocalDateTime

class Member(
        memberId: MemberId?,
        val name: String,
        val email: String,
        val password: String,
        val createdAt: LocalDateTime?,
        val updatedAt: LocalDateTime?,
) : AggregateRoot<MemberId>(memberId) {
    val memberRoles: List<MemberRole>? = null
}

class MemberRole(
        memberRoleId: MemberRoleId?,
        val role: Role,
        val member: Member,
)
package me.jincrates.comma.application.port

import me.jincrates.comma.domain.entity.Member

interface MemberPort {
    fun findByEmail(email: String): Member?
    fun saveMember(member: Member): Member
}
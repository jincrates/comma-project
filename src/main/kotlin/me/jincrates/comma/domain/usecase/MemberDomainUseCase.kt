package me.jincrates.comma.domain.usecase

import me.jincrates.comma.domain.entity.Member

interface MemberDomainUseCase {
    fun createMember(member: Member): Member
}
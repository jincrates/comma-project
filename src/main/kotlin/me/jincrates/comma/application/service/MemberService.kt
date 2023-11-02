package me.jincrates.comma.application.service

import me.jincrates.comma.application.port.MemberPort
import me.jincrates.comma.domain.entity.Member
import me.jincrates.comma.domain.usecase.MemberDomainUseCase
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberPort: MemberPort
) : MemberDomainUseCase {
    override fun createMember(member: Member): Member {
        return memberPort.saveMember(member)
    }
}
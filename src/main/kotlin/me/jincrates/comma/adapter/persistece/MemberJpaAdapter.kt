package me.jincrates.comma.adapter.persistece

import me.jincrates.comma.adapter.persistece.repository.MemberJpaRepository
import me.jincrates.comma.application.port.MemberPort
import me.jincrates.comma.domain.entity.Member
import org.springframework.stereotype.Component

@Component
class MemberJpaAdapter(
        private val memberRepository: MemberJpaRepository
) : MemberPort {
    override fun saveMember(member: Member): Member {
        TODO("Not yet implemented")
    }
}
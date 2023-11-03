package me.jincrates.comma.adapter.persistece

import me.jincrates.comma.adapter.persistece.mapper.MemberMapper
import me.jincrates.comma.adapter.persistece.repository.MemberJpaRepository
import me.jincrates.comma.application.port.MemberPort
import me.jincrates.comma.domain.entity.Member
import org.springframework.stereotype.Component

@Component
class MemberJpaAdapter(
        private val memberMapper: MemberMapper,
        private val memberRepository: MemberJpaRepository,
) : MemberPort {
    override fun findByEmail(email: String): Member? {
        val member = memberRepository.findByEmail(email)
        return member?.let { memberMapper.toDomain(it) };
    }

    override fun saveMember(member: Member): Member {
        val savedMember = memberRepository.save(memberMapper.toEntity(member))
        return memberMapper.toDomain(savedMember)
    }
}
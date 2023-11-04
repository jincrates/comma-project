package me.jincrates.comma.adapter.persistece

import me.jincrates.comma.adapter.persistece.entity.MemberRoleJpaEntity
import me.jincrates.comma.adapter.persistece.mapper.MemberMapper
import me.jincrates.comma.adapter.persistece.repository.MemberJpaRepository
import me.jincrates.comma.adapter.persistece.repository.MemberRoleJpaRepository
import me.jincrates.comma.application.port.MemberPort
import me.jincrates.comma.domain.entity.Member
import me.jincrates.comma.domain.status.Role
import org.springframework.stereotype.Component

@Component
class MemberJpaAdapter(
        private val memberMapper: MemberMapper,
        private val memberRepository: MemberJpaRepository,
        private val memberRoleJpaRepository: MemberRoleJpaRepository,
) : MemberPort {
    override fun findByEmail(email: String): Member? {
        val member = memberRepository.findByEmail(email)
        return member?.let { memberMapper.toDomain(it) };
    }

    override fun saveMember(member: Member): Member {
        val memberEntity = memberMapper.toEntity(member)
        memberEntity.memberRoles = listOf(MemberRoleJpaEntity(null, Role.USER, memberEntity))

        val savedMember = memberRepository.save(memberEntity)
        return memberMapper.toDomain(savedMember)
    }
}
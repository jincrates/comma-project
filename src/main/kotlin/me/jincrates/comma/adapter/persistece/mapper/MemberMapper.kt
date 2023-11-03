package me.jincrates.comma.adapter.persistece.mapper

import me.jincrates.comma.adapter.persistece.entity.MemberJpaEntity
import me.jincrates.comma.domain.entity.Member
import me.jincrates.comma.domain.valueobject.MemberId
import org.springframework.stereotype.Component

@Component
class MemberMapper {
    fun toEntity(domain: Member): MemberJpaEntity {
        return MemberJpaEntity(
                domain.id?.value,
                domain.name,
                domain.email,
                domain.password,
        )
    }

    fun toDomain(entity: MemberJpaEntity): Member {
        return Member(
                entity.id?.let { MemberId(it) },
                entity.name,
                entity.email,
                entity.password,
                entity.createdAt,
                entity.updatedAt,
        )
    }
}
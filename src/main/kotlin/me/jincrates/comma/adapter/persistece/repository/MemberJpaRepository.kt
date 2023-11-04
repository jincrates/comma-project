package me.jincrates.comma.adapter.persistece.repository

import me.jincrates.comma.adapter.persistece.entity.MemberJpaEntity
import me.jincrates.comma.adapter.persistece.entity.MemberRoleJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long> {
    fun findByEmail(email: String): MemberJpaEntity?
}

@Repository
interface MemberRoleJpaRepository : JpaRepository<MemberRoleJpaEntity, Long> {

}
package me.jincrates.comma.adapter.persistece.repository

import me.jincrates.comma.adapter.persistece.entity.MemberJpaEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberJpaEntity, Long>
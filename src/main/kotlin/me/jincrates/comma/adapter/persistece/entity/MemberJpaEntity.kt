package me.jincrates.comma.adapter.persistece.entity

import jakarta.persistence.*
import org.hibernate.annotations.Comment

@Entity
@Table(name = "members")
class MemberJpaEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Comment("회원 id")
        val id: Long? = null,

        @Column(nullable = false, length = 50)
        @Comment("회원 이름")
        val name: String,

        @Column(unique = true, nullable = false, updatable = false)
        @Comment("회원 이메일")
        val email: String,

        @Column(nullable = false)
        @Comment("회원 비밀번호")
        val password: String,
) : BaseJpaEntity()
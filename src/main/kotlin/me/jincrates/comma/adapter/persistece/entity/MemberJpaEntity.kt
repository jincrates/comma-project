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
        var name: String,

        @Column(unique = true, nullable = false)
        var email: String
) : BaseJpaEntity()
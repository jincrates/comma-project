package me.jincrates.comma.adapter.persistece.entity

import jakarta.persistence.*
import me.jincrates.comma.domain.status.Role
import org.hibernate.annotations.Comment

@Entity
@Comment("회원")
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
) : BaseJpaEntity() {
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "member")
    var memberRoles: List<MemberRoleJpaEntity>? = null
}

@Entity
@Comment("회원 권한")
@Table(name = "member_role")
class MemberRoleJpaEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Comment("회원 권한 id")
        val id: Long? = null,

        @Column(nullable = false, length = 30)
        @Enumerated(EnumType.STRING)
        @Comment("회원 권한")
        val role: Role,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        @Comment("회원 id")
        val member: MemberJpaEntity,
) : BaseJpaEntity()
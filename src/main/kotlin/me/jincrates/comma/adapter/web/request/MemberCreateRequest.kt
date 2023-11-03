package me.jincrates.comma.adapter.web.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import me.jincrates.comma.domain.entity.Member
import org.hibernate.validator.constraints.Length

@Schema(description = "회원 등록 request")
data class MemberCreateRequest(
        @field:Schema(description = "회원 이름", example = "진크라테스")
        @field:Length(max = 50, message = "회원 이름은 최대 50자까지만 입력할 수 있습니다.")
        @field:NotBlank(message = "회원 이름은 필수입니다.")
        val name: String,

        @field:Schema(description = "이메일", example = "user@email.com")
        @field:Email(message = "올바른 이메일 형식이 아닙니다.")
        @field:NotBlank(message = "이메일은 필수입니다.")
        val email: String,

        @field:Schema(description = "비밀번호", example = "asdf1234")
        @field:Length(min = 8, message = "비밀번호는 8자 이상이여야합니다.")
        @field:NotBlank(message = "비밀번호는 필수입니다.")
        val password: String,
) {
    fun toDomain(): Member =
            Member(null, name, email, password, null, null)
}
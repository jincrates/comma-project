package me.jincrates.comma.adapter.web.response

import com.fasterxml.jackson.annotation.JsonFormat
import io.swagger.v3.oas.annotations.media.Schema
import me.jincrates.comma.domain.entity.Member
import java.time.LocalDateTime

@Schema(description = "회원 response")
data class MemberResponse(
        @field:Schema(description = "회원 ID", example = "1")
        val id: Long,

        @field:Schema(description = "회원 이름", example = "진크라테스")
        val name: String,

        @field:Schema(description = "이메일", example = "user@email.com")
        val email: String,

        @field:Schema(description = "가입일시")
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        val createdAt: LocalDateTime,

        @field:Schema(description = "수정일시")
        @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
        val updatedAt: LocalDateTime,
) {
    companion object {
        fun toResponse(domain: Member): MemberResponse {
            return MemberResponse(
                    id = domain.id!!.value,
                    name = domain.name,
                    email = domain.email,
                    createdAt = domain.createdAt!!,
                    updatedAt = domain.updatedAt!!,
            )
        }
    }
}
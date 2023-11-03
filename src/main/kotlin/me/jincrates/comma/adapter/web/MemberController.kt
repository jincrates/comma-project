package me.jincrates.comma.adapter.web

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import me.jincrates.comma.adapter.web.request.MemberCreateRequest
import me.jincrates.comma.adapter.web.response.CommonResponse
import me.jincrates.comma.adapter.web.response.MemberResponse
import me.jincrates.comma.domain.usecase.MemberDomainUseCase
import org.springframework.web.bind.annotation.*

@Tag(name = "회원 서비스", description = "회원 등록/조회 API")
@RestController
@RequestMapping("/api/v1/members")
class MemberController(
        private val memberDomainUseCase: MemberDomainUseCase,
) {

    @Operation(summary = "회원 등록")
    @PostMapping("/signup")
    fun createMember(
            @Valid @RequestBody request: MemberCreateRequest,
    ): CommonResponse<MemberResponse> {
        val savedMember = memberDomainUseCase.register(request.toDomain())
        return CommonResponse.created(MemberResponse.toResponse(savedMember))
    }
}
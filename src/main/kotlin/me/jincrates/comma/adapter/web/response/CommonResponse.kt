package me.jincrates.comma.adapter.web.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@Schema(description = "공통 response")
data class CommonResponse<T>(
        @field:Schema(description = "응답 코드", example = "200")
        val code: Int,
        @field:Schema(description = "응답 메시지", example = "")
        val message: String?,
        @field:Schema(description = "응답 데이터")
        val data: T?,
) {
    companion object {
        fun <T> of(httpStatus: HttpStatus, message: String?, data: T?): CommonResponse<T> {
            return CommonResponse(httpStatus.value(), message, data)
        }

        fun <T> of(httpStatus: HttpStatus, data: T?): CommonResponse<T> {
            return of(httpStatus, null, data)
        }

        fun <T> of(httpStatus: HttpStatus): CommonResponse<T> {
            return of(httpStatus, null, null)
        }

        fun <T> ok(data: T?): CommonResponse<T> {
            return of(HttpStatus.OK, data);
        }

        fun <T> created(data: T?): CommonResponse<T> {
            return of(HttpStatus.CREATED, data)
        }

        fun <T> noContent(): CommonResponse<T> {
            return of(HttpStatus.NO_CONTENT)
        }

        fun toResponseEntity(status: HttpStatus, message: String?): ResponseEntity<*> {
            return ResponseEntity.status(status).body(of(status, message, null))
        }
    }
}

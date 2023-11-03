package me.jincrates.comma.global.exception

import jakarta.persistence.EntityNotFoundException
import me.jincrates.comma.adapter.web.response.CommonResponse
import me.jincrates.comma.domain.exception.DomainException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException

@RestControllerAdvice
class GlobalExceptionHandler {

    /**
     * 도메인 에러
     */
    @ExceptionHandler(DomainException::class)
    protected fun handleDomainException(exception: DomainException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    protected fun handleIllegalArgumentException(exception: IllegalArgumentException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }

    @ExceptionHandler(BindException::class)
    protected fun handlerBindException(exception: BindException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }

    /**
     * javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     * HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     * 주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(exception: MethodArgumentNotValidException): ResponseEntity<*> {
        val message = getMessageFromBindingResult(exception.bindingResult)
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, message)
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    protected fun handleMethodArgumentTypeMismatchException(exception: MethodArgumentTypeMismatchException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }

    @ExceptionHandler(MissingRequestHeaderException::class)
    protected fun handleMissingRequestHeaderException(exception: MissingRequestHeaderException): ResponseEntity<*> {
        val message = when (exception.headerName) {
            HttpHeaders.AUTHORIZATION -> "헤더에 인증정보가 없습니다."
            else -> exception.message
        }

        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, message)
    }

    /**
     * RestTemplate API 클라이언트 예외 처리
     */
    @ExceptionHandler(HttpClientErrorException::class)
    protected fun handleHttpClientErrorException(exception: HttpClientErrorException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }


    /**
     * RestTemplate API 클라이언트 예외 처리
     */
    @ExceptionHandler(RestClientException::class)
    protected fun handleHttpServerErrorException(exception: RestClientException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.BAD_REQUEST, exception.message)
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    protected fun handleHttpRequestMethodNotSupportedException(exception: HttpRequestMethodNotSupportedException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.METHOD_NOT_ALLOWED, exception.message)
    }

    /**
     * RestTemplate API 서버 예외 처리
     */
    @ExceptionHandler(HttpServerErrorException::class)
    protected fun handleHttpServerErrorException(exception: HttpServerErrorException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
    }

    @ExceptionHandler(EntityNotFoundException::class)
    protected fun handlerEntityNotFoundException(exception: EntityNotFoundException): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, exception.message)
    }

    /**
     * 예상하지 못한 에러
     */
    @ExceptionHandler(Exception::class)
    protected fun handleException(exception: Exception): ResponseEntity<*> {
        return CommonResponse.toResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.reasonPhrase)
    }

    private fun getMessageFromBindingResult(bindingResult: BindingResult): String {
        val error = bindingResult.fieldErrors[0]
        return "${error.field}(은)는 ${error.defaultMessage} 입력된 값: [${error.rejectedValue}]"
    }
}
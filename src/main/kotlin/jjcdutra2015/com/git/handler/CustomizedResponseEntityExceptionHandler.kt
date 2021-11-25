package jjcdutra2015.com.git.handler

import jjcdutra2015.com.git.exception.ExceptionResponse
import jjcdutra2015.com.git.exception.UnsupportedMathOperationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDate

@RestController
@ControllerAdvice
class CustomizedResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(LocalDate.now(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(UnsupportedMathOperationException::class)
    fun handleBadRequestException(ex: Exception, request: WebRequest): ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(LocalDate.now(), ex.message.toString(), request.getDescription(false))
        return ResponseEntity(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}
package jjcdutra2015.com.git.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class UnsupportedMathOperationException(message: String) : RuntimeException(message)
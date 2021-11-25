package jjcdutra2015.com.git.exception

import java.time.LocalDate

data class ExceptionResponse(
    val timestamp: LocalDate,
    val message: String,
    val details: String
)
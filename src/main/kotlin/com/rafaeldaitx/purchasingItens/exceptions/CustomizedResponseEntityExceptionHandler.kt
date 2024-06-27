package com.rafaeldaitx.purchasingItens.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.lang.Exception
import java.util.*

class CustomizedResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(exception: Exception, request: WebRequest)
                            :ResponseEntity<ExceptionResponse>{
        val exceptionResponse = ExceptionResponse(
            Date(),
            exception.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    fun handleResourceNotFoundException(exception: Exception, request: WebRequest)
            :ResponseEntity<ExceptionResponse>{
        val exceptionResponse = ExceptionResponse(
            Date(),
            exception.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(RequiredObjectsNullException::class)
    fun handleBadRequestExceptions(exception: Exception, request: WebRequest)
            : ResponseEntity<ExceptionResponse> {
        val exceptionResponse = ExceptionResponse(
            Date(),
            exception.message,
            request.getDescription(false)
        )
        return ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST)
    }
}
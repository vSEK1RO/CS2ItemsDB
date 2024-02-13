package net.stauc.cs2itemsdb.controller

import net.stauc.cs2itemsdb.model.ApiResponse
import net.stauc.cs2itemsdb.model.exception.AbstractApiException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionResolver {
    @ExceptionHandler(value = [AbstractApiException::class])
    fun handle(cause: AbstractApiException, request: WebRequest): ResponseEntity<ApiResponse> {
        return cause.asResponse()
    }

}
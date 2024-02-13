package net.stauc.cs2itemsdb.model.exception

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import net.stauc.cs2itemsdb.model.ApiResponse
import org.springframework.http.HttpStatus

@JsonIgnoreProperties("cause", "stackTrace", "suppressed", "localizedMessage")
abstract class AbstractApiException : ApiResponse, Exception() {
    override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
    override val message: String
        get() = localizedMessage
}

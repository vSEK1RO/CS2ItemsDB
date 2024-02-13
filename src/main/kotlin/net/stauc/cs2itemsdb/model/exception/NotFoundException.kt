package net.stauc.cs2itemsdb.model.exception

import org.springframework.http.HttpStatus

class NotFoundException : AbstractApiException() {
    override val message: String
        get() = "Not found"
    override val status: HttpStatus = HttpStatus.NOT_FOUND
}

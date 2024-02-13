package backend.workshop.model.message

import net.stauc.cs2itemsdb.model.ApiResponse
import org.springframework.http.HttpStatus

abstract class AbstractApiMessage : ApiResponse {
    override val status: HttpStatus = HttpStatus.OK
}

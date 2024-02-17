package net.stauc.cs2itemsdb.controller.impl

import backend.workshop.model.message.HealthMessage
import net.stauc.cs2itemsdb.controller.HealthController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthControllerImpl : HealthController {
    @GetMapping("/HealthService")
    override fun health() = HealthMessage()
}
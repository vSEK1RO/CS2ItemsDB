package net.stauc.cs2itemsdb.controller

import backend.workshop.model.message.HealthMessage

interface HealthController {
    fun health(): HealthMessage
}

package net.stauc.cs2itemsdb.model.response

import java.time.LocalDateTime

interface EntityResponse {
    val id: Long
    val updatedAt: LocalDateTime
}

package net.stauc.cs2itemsdb.model.response

import jakarta.persistence.Column
import kotlinx.serialization.json.long
import java.time.LocalDateTime

class ItemResponse(
    override val id: Long,
    val updatedAt: LocalDateTime,
    val hashName: String,
    var sellListings: Long? = null,
    var sellPriceText: String? = null,
    var salePriceText: String? = null,
    var appid: Long? = null,
    var classid: Long? = null,
    var instanceid: Long? = null,
    var tradable: Long? = null,
    var type: String? = null,
    var commodity: Long? = null,
    var marketTradableRestriction: Long? = null,
) : EntityResponse

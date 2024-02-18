package net.stauc.cs2itemsdb.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "items")
class ItemEntity(

    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "hash_name", columnDefinition = "TEXT")
    var hashName: String,

    @Column(name = "sell_listings", nullable = true)
    var sellListings: Long? = null,

    @Column(name = "sell_price_text", nullable = true, columnDefinition = "TEXT")
    var sellPriceText: String? = null,

    @Column(name = "sale_price_text", nullable = true, columnDefinition = "TEXT")
    var salePriceText: String? = null,

    @Column(name = "appid", nullable = true)
    var appid: Long? = null,

    @Column(name = "classid", nullable = true)
    var classid: Long? = null,

    @Column(name = "instanceid", nullable = true)
    var instanceid: Long? = null,

    @Column(name = "tradable", nullable = true)
    var tradable: Long? = null,

    @Column(name = "type", nullable = true, columnDefinition = "TEXT")
    var type: String? = null,

    @Column(name = "commodity", nullable = true)
    var commodity: Long? = null,

    @Column(name = "market_tradable_restriction", nullable = true)
    var marketTradableRestriction: Long? = null,

) : AbstractEntity()


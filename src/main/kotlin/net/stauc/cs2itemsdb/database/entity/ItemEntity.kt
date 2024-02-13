package net.stauc.cs2itemsdb.database.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class ItemEntity(
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
    var tradable: Boolean? = null,

    @Column(name = "type", nullable = true, columnDefinition = "TEXT")
    var type: String? = null,

    @Column(name = "commodity", nullable = true)
    var commodity: Boolean? = null,

    @Column(name = "market_tradable_restriction", nullable = true)
    var marketTradableRestriction: Long? = null,

    @Column(name = "descriptions", nullable = true, columnDefinition = "TEXT")
    var descriptions: String? = null,
) : AbstractEntity()


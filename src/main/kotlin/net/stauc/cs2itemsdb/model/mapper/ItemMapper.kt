package net.stauc.cs2itemsdb.model.mapper

import net.stauc.cs2itemsdb.database.entity.ItemEntity
import net.stauc.cs2itemsdb.model.request.ItemRequest
import net.stauc.cs2itemsdb.model.response.ItemResponse
import org.springframework.stereotype.Component

@Component
class ItemMapper {
    fun asEntity(request: ItemRequest) = ItemEntity(
        hashName = request.hashName,
    )

    fun asResponse(entity: ItemEntity) = ItemResponse(
        id = entity.id,
        updatedAt = entity.updatedAt,
        hashName = entity.hashName,
        sellListings = entity.sellListings,
        sellPriceText = entity.sellPriceText,
        salePriceText = entity.salePriceText,
        appid = entity.appid,
        classid = entity.classid,
        instanceid = entity.instanceid,
        tradable = entity.tradable,
        type = entity.type,
        commodity = entity.commodity,
        marketTradableRestriction = entity.marketTradableRestriction,
    )

    fun asListResponse(items: Iterable<ItemEntity>) : List<ItemResponse>{
        return items.map { asResponse(it) }
    }
}
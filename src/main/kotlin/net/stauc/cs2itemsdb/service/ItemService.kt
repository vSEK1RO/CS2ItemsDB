package net.stauc.cs2itemsdb.service

import net.stauc.cs2itemsdb.model.response.ItemResponse

interface ItemService {
    fun list(): List<ItemResponse>
    fun getByHashName(hashName: String): ItemResponse
}
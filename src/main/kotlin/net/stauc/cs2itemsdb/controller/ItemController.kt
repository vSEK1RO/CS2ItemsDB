package net.stauc.cs2itemsdb.controller

import net.stauc.cs2itemsdb.model.response.ItemResponse

interface ItemController {
    fun list(): List<ItemResponse>
    fun getByHashName(hashName: String): ItemResponse
}
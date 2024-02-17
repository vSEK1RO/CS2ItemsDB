package net.stauc.cs2itemsdb.controller

import net.stauc.cs2itemsdb.model.response.HashNameResponse
import net.stauc.cs2itemsdb.model.response.ItemResponse

interface ItemController {
    fun list(): List<HashNameResponse>
    fun getByHashName(hashName: String): ItemResponse
}
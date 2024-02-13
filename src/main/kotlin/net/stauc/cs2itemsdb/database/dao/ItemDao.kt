package net.stauc.cs2itemsdb.database.dao

import net.stauc.cs2itemsdb.database.entity.ItemEntity

interface ItemDao : CommonDao<ItemEntity> {
    fun findByHashName(hashName: String): ItemEntity?
}
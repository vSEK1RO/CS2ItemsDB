package net.stauc.cs2itemsdb.database.dao

import net.stauc.cs2itemsdb.database.entity.ItemEntity
import org.springframework.data.jpa.repository.Query

interface ItemDao : CommonDao<ItemEntity> {
    fun findByHashName(hashName: String): ItemEntity?
    @Query(
        value = "SELECT * FROM items " +
                "ORDER by updated_at " +
                "LIMIT 1 OFFSET 0 ",
        nativeQuery = true,
    )
    fun findOldestUpdated(): ItemEntity?
}
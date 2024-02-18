package net.stauc.cs2itemsdb.database.dao

import net.stauc.cs2itemsdb.database.entity.ItemEntity
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ItemDao : CommonDao<ItemEntity> {
    @Query(
        value = "SELECT * FROM items " +
                "WHERE hash_name LIKE :hashName  " +
                "LIMIT 1 OFFSET 0 ",
        nativeQuery = true,
    )
    fun findByHashName(@Param("hashName") hashName: String): ItemEntity?
    @Query(
        value = "SELECT * FROM items " +
                "ORDER by updated_at " +
                "LIMIT 1 OFFSET 0 ",
        nativeQuery = true,
    )
    fun findOldestUpdated(): ItemEntity?
}
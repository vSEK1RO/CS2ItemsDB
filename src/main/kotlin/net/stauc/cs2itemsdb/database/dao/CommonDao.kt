package net.stauc.cs2itemsdb.database.dao

import net.stauc.cs2itemsdb.database.entity.AbstractEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface CommonDao<T : AbstractEntity> : CrudRepository<T, Long> {
    fun findEntityById(id: Long): T?
}

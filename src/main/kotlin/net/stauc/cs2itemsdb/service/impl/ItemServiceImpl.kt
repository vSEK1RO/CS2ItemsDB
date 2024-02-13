package net.stauc.cs2itemsdb.service.impl

import net.stauc.cs2itemsdb.database.dao.ItemDao
import net.stauc.cs2itemsdb.model.exception.NotFoundException
import net.stauc.cs2itemsdb.model.mapper.ItemMapper
import net.stauc.cs2itemsdb.model.response.ItemResponse
import net.stauc.cs2itemsdb.service.ItemService
import org.springframework.stereotype.Service

@Service
class ItemServiceImpl(
    private val dao: ItemDao,
    private val mapper: ItemMapper,
) : ItemService {
    override fun list(): List<ItemResponse> {
        val items = dao.findAll()
        return mapper.asListResponse(items)
    }

    override fun getByHashName(hashName: String): ItemResponse {
        val item = dao.findByHashName(hashName) ?: throw NotFoundException()
        return mapper.asResponse(item)
    }
}
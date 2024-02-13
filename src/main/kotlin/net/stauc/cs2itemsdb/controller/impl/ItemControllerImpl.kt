package net.stauc.cs2itemsdb.controller.impl

import net.stauc.cs2itemsdb.controller.ItemController
import net.stauc.cs2itemsdb.model.response.ItemResponse
import net.stauc.cs2itemsdb.service.ItemService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/ItemService")
class ItemControllerImpl(
    private val service: ItemService,
) : ItemController {
    @GetMapping
    override fun list()
        = service.list()

    @GetMapping("/{hashName}")
    override fun getByHashName(@PathVariable hashName: String)
        = service.getByHashName(hashName)
}
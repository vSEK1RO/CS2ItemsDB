package net.stauc.cs2itemsdb

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import net.stauc.cs2itemsdb.database.dao.ItemDao
import net.stauc.cs2itemsdb.database.entity.ItemEntity
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

@SpringBootApplication
class Cs2ItemsDbApplication

fun main(args: Array<String>) {
    runApplication<Cs2ItemsDbApplication>(*args)
}
package net.stauc.cs2itemsdb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import net.stauc.cs2itemsdb.database.dao.ItemDao
import net.stauc.cs2itemsdb.database.entity.ItemEntity
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.time.Duration
import java.time.LocalDateTime

@SpringBootApplication
class Cs2ItemsDbApplication

fun main(args: Array<String>) {
    runApplication<Cs2ItemsDbApplication>(*args)
}
@Component
class ParserComponent(
    private val dao: ItemDao
) : Thread(){
    @PostConstruct
    fun initialise(){
        this.start()
    }

    override fun run() {
        var oldestUpdatedId = dao.findOldestUpdated()?.id ?: 0
        while (true) {
            var twentyCounter = 20
            while (twentyCounter > 0) {
                twentyCounter--
                val url = URL(
                    "https://steamcommunity.com/market/search/render/?start=$oldestUpdatedId" +
                            "&count=100&search_descriptions=0&sort_column=popular&sort_dir=desc&norender=1&appid=730&currency=1"
                )
                oldestUpdatedId+=100
                var res = ""
                val connection = url.openConnection()
                try{
                    BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                        var line: String?
                        while (inp.readLine().also { line = it } != null) {
                            res += line
                        }
                    }
                }catch (e: IOException){
                    Thread.sleep(Duration.ofSeconds(60))
                    twentyCounter=20
                    continue
                }
                val results = Json.parseToJsonElement(res).jsonObject["results"]?.jsonArray ?: buildJsonArray {}
                for(i in 0..<results.size){
                    val item = results.get(i).jsonObject ?: break
                    val asset_description = item["asset_description"]?.jsonObject ?: break
                    val entity = ItemEntity(
                        updatedAt = LocalDateTime.now(),
                        hashName = item["hash_name"]?.jsonPrimitive.toString().replace(Regex("^\"|\"$"), ""),
                        sellListings = item["sell_listings"]?.jsonPrimitive?.long,
                        sellPriceText = item["sell_price_text"]?.jsonPrimitive.toString().replace(Regex("^\"|\"$"), ""),
                        salePriceText = item["sale_price_text"]?.jsonPrimitive.toString().replace(Regex("^\"|\"$"), ""),
                        appid = asset_description["appid"]?.jsonPrimitive?.long,
                        classid = asset_description["classid"]?.jsonPrimitive?.long,
                        instanceid = asset_description["instanceid"]?.jsonPrimitive?.long,
                        tradable = asset_description["tradable"]?.jsonPrimitive?.long,
                        type = asset_description["type"]?.jsonPrimitive.toString().replace(Regex("^\"|\"$"), ""),
                        commodity = asset_description["commodity"]?.jsonPrimitive?.long,
                        marketTradableRestriction = asset_description["market_tradable_restriction"]?.jsonPrimitive?.long,
                    )
                    entity.id = dao.findByHashName(entity.hashName)?.id ?: 0
                    dao.save(entity)
                }
            }
            Thread.sleep(Duration.ofSeconds(60))
        }
    }
}
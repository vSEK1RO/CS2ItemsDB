package net.stauc.cs2itemsdb

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import jakarta.annotation.PostConstruct
import kotlinx.coroutines.*
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

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun run() {
        runBlocking {
            var oldestUpdatedId = 0
            var totalCount = 20936
            val coroutineMax = 25
            var coroutineCounter = 0
            while(true){
                coroutineCounter = 0
                val request = launch {
                    while (coroutineCounter < coroutineMax) {
                        coroutineCounter++
                        val oldestLaunch = oldestUpdatedId
                        launch {
                            val url = URL(
                                    "https://steamcommunity.com/market/search/render/?start=$oldestLaunch" +
                                            "&count=100&search_descriptions=0&sort_column=popular&sort_dir=desc&norender=1&appid=730&currency=1"
                            )
                            var res = ""
                            println("${LocalDateTime.now()} sent request start=$oldestLaunch")
                            val connection = url.openConnection()
                            try{
                                BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                                    var line: String?
                                    while (inp.readLine().also { line = it } != null) {
                                        res += line
                                    }
                                }
                            }catch (e: IOException){
                                coroutineCounter=coroutineMax
                                println("${LocalDateTime.now()}  caught response $e")
                                println("${LocalDateTime.now()}  waiting for 60 seconds ...")
                                Thread.sleep(60000)
                                return@launch
                            }
                            totalCount=Json.parseToJsonElement(res).jsonObject["total_count"]?.jsonPrimitive?.int ?: 0
                            val requestStart=Json.parseToJsonElement(res).jsonObject["start"]?.jsonPrimitive?.long ?: 0
                            println("${LocalDateTime.now()}  caught response start=$requestStart | $totalCount")
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
                                launch {
                                    dao.save(entity)
                                }
                            }
                        }
                        oldestUpdatedId += 100
                        if (oldestUpdatedId > totalCount - 100) {
                            oldestUpdatedId = 0
                        }
                    }
                }
                request.join()
                println("${LocalDateTime.now()}  waiting for 60 seconds ...")
                Thread.sleep(60000)
            }
        }
    }
}
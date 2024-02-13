import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import net.stauc.cs2itemsdb.database.dao.ItemDao
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class ParserComponent(
    private val dao: ItemDao
) : InitializingBean {
    override fun afterPropertiesSet() {
        var oldestUpdatedId = dao.findOldestUpdated()?.id ?: 0
        println("abob1")
        runBlocking {
            while(true){
                println("abob2")
                var twentyCounter = 20
                while (twentyCounter>0) {
                    println("abob3")
                    launch {
//                        val url = URL(
//                            "https://steamcommunity.com/market/search/render/?start=" + oldestUpdatedId +
//                                    "&count=100&search_descriptions=0&sort_column=name&sort_dir=desc&norender=1&appid=730&currency=1"
//                        )
//                        var res = ""
//                        val connection = url.openConnection()
//                        BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
//                            var line: String?
//                            while (inp.readLine().also { line = it } != null) {
//                                res += line
//                            }
//                        }
//                        val obj = Json.parseToJsonElement(res)
//                        val results = obj.jsonObject["results"]?.jsonArray
//                        println(obj)
                        delay(1000)
                        println("aboba")
                        twentyCounter--
                    }
                }
            }
        }
    }
}
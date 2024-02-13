package net.stauc.cs2itemsdb

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Cs2ItemsDbApplication

fun main(args: Array<String>) {
    runApplication<Cs2ItemsDbApplication>(*args)
}
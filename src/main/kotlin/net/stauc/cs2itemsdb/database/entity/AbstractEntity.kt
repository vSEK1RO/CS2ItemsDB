package net.stauc.cs2itemsdb.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
}

package com.sk.model.band

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp
import org.ktorm.schema.varchar
import java.time.Instant

// Define the Band entity interface
interface Band : Entity<Band> {
    companion object : Entity.Factory<Band>()

    val id: Int
    var name: String
    var createdAt: Instant
}

// Define the Bands table object
object Bands : Table<Band>("band") {
    val id = int("id").primaryKey().bindTo { it.id }
    val name = varchar("name").bindTo { it.name }
    val createdAt = timestamp("created_at").bindTo { it.createdAt }
}

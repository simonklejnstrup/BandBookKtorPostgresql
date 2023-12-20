package com.example.model

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.long
import org.ktorm.schema.varchar
import org.ktorm.schema.timestamp
import java.time.Instant

interface User: Entity<User> {
        companion object : Entity.Factory<User>()
        val id: Long?
        var firstname: String
        var lastname: String
        var email: String
        var created: Instant
    }
    object Users : Table<User>("app_user") {
        val id = long("id").primaryKey().bindTo(User::id)
        val firstname = varchar("firstname").bindTo(User::firstname)
        val lastname = varchar("lastname").bindTo(User::lastname)
        val email = varchar("email").bindTo(User::email)
        val created = timestamp("created_at").bindTo(User::created)


}
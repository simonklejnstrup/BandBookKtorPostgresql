package com.sk.util

import org.ktorm.database.Database

object DatabaseFactory {
    val database: Database by lazy {
        init()
    }

    private fun init(): Database {
        val dbUrl = System.getenv("DB_URL") ?: throw IllegalStateException("DB_URL not set in env")
        val dbUser = System.getenv("DB_USER") ?: throw IllegalStateException("DB_USER not set in env")
        val dbPassword = System.getenv("DB_PASSWORD") ?: throw IllegalStateException("DB_PASSWORD not set in env")

        return Database.connect(
            url = dbUrl,
            driver = "org.postgresql.Driver",
            user = dbUser,
            password = dbPassword
        )
    }

}

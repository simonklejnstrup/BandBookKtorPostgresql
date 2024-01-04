package com.sk

import com.sk.plugins.*
import com.sk.route.configureCommentRoutes
import com.sk.route.configureEventRoutes
import com.sk.route.configureForumThreadRoutes
import com.sk.route.configureUserRoutes
import com.sk.util.DatabaseFactory
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {    
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
//        configureDatabase()
        configureRouting()
    }.start(wait = true)
}

//fun Application.configureDatabase() {
//    DatabaseFactory.init()
//}

fun Application.module() {
    configureSerialization()
}

fun Application.configureRouting() {
    configureUserRoutes()
    configureEventRoutes()
    configureForumThreadRoutes()
    configureCommentRoutes()
    configureSerialization()
}

///

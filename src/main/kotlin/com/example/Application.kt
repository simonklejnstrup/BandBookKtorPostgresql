package com.example

import com.example.plugins.*
import com.example.route.configureCommentRoutes
import com.example.route.configureEventRoutes
import com.example.route.configureForumThreadRoutes
import com.example.route.configureUserRoutes
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {    
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureUserRoutes()
        configureEventRoutes()
        configureForumThreadRoutes()
        configureCommentRoutes()
        configureSerialization()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
}

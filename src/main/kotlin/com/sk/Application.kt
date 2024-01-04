package com.sk

import com.sk.plugins.*
import com.sk.route.configureCommentRoutes
import com.sk.route.configureEventRoutes
import com.sk.route.configureForumThreadRoutes
import com.sk.route.configureUserRoutes
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

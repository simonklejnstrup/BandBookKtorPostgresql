package com.example

import com.example.plugins.*
import com.example.route.configureBookRoutes
import com.example.route.configureUserRoutes
import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        configureBookRoutes()
        configureUserRoutes()
        configureSerialization()
    }.start(wait = true)
}

fun Application.module() {
    configureSerialization()
}

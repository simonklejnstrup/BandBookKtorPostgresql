package com.sk.plugins

import com.sk.util.InstantSerializer
import com.sk.util.LocalDateSerializer
import com.sk.util.LocalTimeSerializer
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.Instant
import java.time.LocalDate
import java.time.LocalTime

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            serializersModule = SerializersModule {
                contextual(Instant::class, InstantSerializer)
                contextual(LocalDate::class, LocalDateSerializer)
                contextual(LocalTime::class, LocalTimeSerializer)
            }
        }
        )
    }
}

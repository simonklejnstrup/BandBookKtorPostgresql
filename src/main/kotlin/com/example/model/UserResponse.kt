package com.example.model

import com.example.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class UserResponse(
    val id: Long,
    val firstname: String,
    var lastname: String,
    var email: String,
    @Serializable(with = InstantSerializer::class)
    var created: Instant
)

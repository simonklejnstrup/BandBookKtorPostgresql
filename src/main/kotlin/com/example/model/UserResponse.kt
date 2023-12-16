package com.example.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class UserResponse(
    val id: Long,
    val firstname: String,
    var lastname: String,
    var email: String,
//    @Contextual
//    var created: Instant
)

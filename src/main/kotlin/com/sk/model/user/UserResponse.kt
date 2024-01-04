package com.sk.model.user

import com.sk.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class UserResponse(
    val id: Long,
    val firstname: String,
    var lastname: String,
    var email: String,
    var band: String,
    @Serializable(with = InstantSerializer::class)
    var created: Instant
)

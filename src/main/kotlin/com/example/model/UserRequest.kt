package com.example.model

import kotlinx.serialization.Contextual
import java.time.Instant

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest (
    var firstname: String,
    var lastname: String,
    var email: String,
//    @Contextual
//    var created: Instant
)

package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest (
    var firstname: String,
    var lastname: String,
    var email: String,
    // Created skal ikke håndteres her
//    @Contextual
//    var created: Instant
)

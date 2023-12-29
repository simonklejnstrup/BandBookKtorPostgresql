package com.example.model.user

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest (
    var firstname: String,
    var lastname: String,
    var email: String,
    var band: String
)

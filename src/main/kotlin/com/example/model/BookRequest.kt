package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class BookRequest(
    val name: String
)
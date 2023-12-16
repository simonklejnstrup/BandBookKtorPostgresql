package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val id: Long,
    val name: String
)
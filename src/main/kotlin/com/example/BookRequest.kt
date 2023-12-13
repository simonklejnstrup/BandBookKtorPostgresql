package com.example

import kotlinx.serialization.Serializable

@Serializable
data class BookRequest(
    val name: String
)
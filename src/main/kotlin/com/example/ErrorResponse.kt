package com.example

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String)
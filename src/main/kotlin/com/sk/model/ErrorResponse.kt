package com.sk.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String)
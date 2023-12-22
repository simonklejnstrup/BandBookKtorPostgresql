package com.example.model.comment

import com.example.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CommentRequest(
    val content: String,
    val createdBy: String,
    val threadId: Int
)


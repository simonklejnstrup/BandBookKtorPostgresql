package com.sk.model.comment

import com.sk.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class CommentResponse(
    val id: Int,
    val createdBy: String,
    val content: String,
    @Serializable(with = InstantSerializer::class)
    val createdDate: Instant,
    val threadId: Int
)

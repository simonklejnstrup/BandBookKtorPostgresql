package com.sk.model.comment

import kotlinx.serialization.Serializable

@Serializable
data class CommentRequest(
    val content: String,
    val createdBy: String,
    val threadId: Int
)


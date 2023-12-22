package com.example.model.forumthread

import com.example.util.InstantSerializer
import kotlinx.serialization.Serializable
import java.time.Instant

@Serializable
data class ForumThreadResponse(
    val id: Int,
    val title: String,
    val createdBy: String,
    @Serializable(with = InstantSerializer::class)
    val createdDate: Instant
)

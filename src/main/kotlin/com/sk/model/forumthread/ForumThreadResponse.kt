package com.sk.model.forumthread

import com.sk.util.InstantSerializer
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

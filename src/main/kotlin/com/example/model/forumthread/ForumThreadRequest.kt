package com.example.model.forumthread

import kotlinx.serialization.Serializable

@Serializable
data class ForumThreadRequest(
    val title: String,
    val createdBy: String
)

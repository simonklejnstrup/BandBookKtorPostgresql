package com.example.model.forumthreadwithcomments

import com.example.model.comment.CommentResponse
import com.example.model.forumthread.ForumThreadResponse
import kotlinx.serialization.Serializable

@Serializable
data class ForumThreadWithCommentsResponse(
    val thread: ForumThreadResponse?,
    val comments: List<CommentResponse?>
)

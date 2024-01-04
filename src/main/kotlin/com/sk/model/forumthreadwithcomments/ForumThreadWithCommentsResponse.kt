package com.sk.model.forumthreadwithcomments

import com.sk.model.comment.CommentResponse
import com.sk.model.forumthread.ForumThreadResponse
import kotlinx.serialization.Serializable

@Serializable
data class ForumThreadWithCommentsResponse(
    val thread: ForumThreadResponse?,
    val comments: List<CommentResponse?>
)

package com.example.service

import com.example.model.comment.Comment
import com.example.model.comment.CommentRequest
import com.example.model.comment.Comments
import com.example.model.forumthread.ForumThread
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.*
import java.time.Instant

class CommentService {
    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5438/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    fun createComment(commentRequest: CommentRequest): Boolean {
        val newComment = Comment {
            createdBy = commentRequest.createdBy
            content = commentRequest.content
            threadId = commentRequest.threadId
        }
        val affectedRecordsNumber =
            database.sequenceOf(Comments).add(newComment)

        return affectedRecordsNumber == 1
    }

    fun findAllComments(): Set<Comment> =
        database.sequenceOf(Comments).toSet()

    fun findCommentById(commentId: Int): Comment? =
        database.sequenceOf(Comments)
            .find { comment -> comment.id.eq(commentId) }

    fun findCommentsByThreadId(threadId: Int): List<Comment> {
        return database.sequenceOf(Comments)
            .filter { it.threadId eq threadId }
            .toList()
    }


    fun updateCommentById(commentId: Int, commentRequest: CommentRequest): Boolean {
        val foundComment = findCommentById(commentId)
        foundComment?.content = commentRequest.content

        val affectedRecordsNumber = foundComment?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteCommentById(commentId: Int): Boolean {
        val foundComment = findCommentById(commentId)

        val affectedRecordsNumber = foundComment?.delete()

        return affectedRecordsNumber == 1
    }
}
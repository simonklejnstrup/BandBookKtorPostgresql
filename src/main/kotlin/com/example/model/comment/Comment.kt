package com.example.model.comment

import com.example.model.forumthread.ForumThread
import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.Instant


interface Comment : Entity<Comment> {
    companion object : Entity.Factory<Comment>()

    val id: Int
    var content: String
    var createdBy: String
    var createdDate: Instant
    var threadId: Int
    // Reference to the associated ForumThread entity
    var forumThread: ForumThread
}

object Comments : Table<Comment>("comment") {
    val id = int("id").primaryKey().bindTo { it.id }
    val content = text("content").bindTo { it.content }
    val createdBy = varchar("created_by").bindTo { it.createdBy }
    val createdDate = timestamp("created_date").bindTo { it.createdDate }
    val threadId = int("thread_id").bindTo { it.threadId }
}







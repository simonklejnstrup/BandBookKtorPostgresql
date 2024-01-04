package com.sk.model.forumthread

import org.ktorm.entity.Entity
import org.ktorm.schema.Table
import org.ktorm.schema.int
import org.ktorm.schema.timestamp
import org.ktorm.schema.varchar
import java.time.Instant

interface ForumThread : Entity<ForumThread> {
    companion object : Entity.Factory<ForumThread>()

    val id: Int
    var title: String
    var createdBy: String
    var createdAt: Instant
}

object ForumThreads : Table<ForumThread>("forum_thread") {
    val id = int("id").primaryKey().bindTo { it.id }
    val title = varchar("title").bindTo { it.title }
    val createdBy = varchar("created_by").bindTo { it.createdBy }
    val createdAt = timestamp("created_at").bindTo { it.createdAt }
}

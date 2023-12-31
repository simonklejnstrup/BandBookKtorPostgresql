package com.sk.service

import com.sk.model.forumthread.ForumThread
import com.sk.model.forumthread.ForumThreadRequest
import com.sk.model.forumthread.ForumThreads
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class ForumThreadService(private val database: Database) {

    fun createForumThread(forumThreadRequest: ForumThreadRequest): Boolean {
        val newForumThread = ForumThread {
            title = forumThreadRequest.title
            createdBy = forumThreadRequest.createdBy
        }

        val affectedRecordsNumber =
            database.sequenceOf(ForumThreads).add(newForumThread)

        return affectedRecordsNumber == 1
    }

    fun findAllForumThreads(): Set<ForumThread> =
        database.sequenceOf(ForumThreads).toSet()

    fun findForumThreadById(forumThreadId: Int): ForumThread? =
        database.sequenceOf(ForumThreads)
            .find { forumThread ->  forumThread.id.eq(forumThreadId)  }

    fun updateForumThreadById(forumThreadId: Int, forumThreadRequest: ForumThreadRequest): Boolean {
        val foundForumThread = findForumThreadById(forumThreadId)
        foundForumThread?.title = forumThreadRequest.title

        val affectedRecordsNumber = foundForumThread?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteForumThreadById(forumThreadId: Int): Boolean {
        val foundForumThread = findForumThreadById(forumThreadId)

        val affectedRecordsNumber = foundForumThread?.delete()

        return affectedRecordsNumber == 1
    }
}
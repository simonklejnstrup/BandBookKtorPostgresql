package com.sk.route

import com.sk.model.*
import com.sk.model.comment.Comment
import com.sk.model.forumthread.ForumThread
import com.sk.model.forumthread.ForumThreadRequest
import com.sk.model.forumthread.ForumThreadResponse
import com.sk.model.forumthreadwithcomments.ForumThreadWithCommentsResponse
import com.sk.service.CommentService
import com.sk.service.ForumThreadService
import com.sk.util.DatabaseFactory
import com.sk.util.getIdParam
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private fun ForumThread?.toForumThreadResponse(): ForumThreadResponse? =
    this?.let { ForumThreadResponse(
        it.id!!,
        it.title,
        it.createdBy,
        it.createdAt,
    )
    }

fun Application.configureForumThreadRoutes() {
    routing {
        route("/api/v1/forumthread") {
            val forumThreadService = ForumThreadService(DatabaseFactory.database)
            val commentService = CommentService(DatabaseFactory.database)
            createForumThread(forumThreadService)
            getAllForumThreadsRoute(forumThreadService)
            getForumThreadByIdRoute(forumThreadService)
            updateForumThreadByIdRoute(forumThreadService)
            deleteForumThreadByIdRoute(forumThreadService)
            getThreadWithCommentsRoute(commentService, forumThreadService)
        }
    }
}

fun Route.createForumThread(forumThreadService: ForumThreadService) {
    post {
        val request = call.receive<ForumThreadRequest>()

        val success = forumThreadService.createForumThread(forumThreadRequest = request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create forumThread"))
    }
}

fun Route.getAllForumThreadsRoute(forumThreadService: ForumThreadService) {
    get {
        val forumThreads = forumThreadService.findAllForumThreads()
            .map(ForumThread::toForumThreadResponse)

        call.respond(message = forumThreads)
    }
}

fun Route.getForumThreadByIdRoute(forumThreadService: ForumThreadService) {
    get("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        forumThreadService.findForumThreadById(id)
            ?.let { foundForumThread -> foundForumThread.toForumThreadResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("ForumThread with id [$id] not found"))
    }
}

fun Route.updateForumThreadByIdRoute(forumThreadService: ForumThreadService) {
    patch("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@patch call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val request = call.receive<ForumThreadRequest>()
        val success = forumThreadService.updateForumThreadById(id, request)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot update forumThread with id [$id]"))
    }
}

fun Route.deleteForumThreadByIdRoute(forumThreadService: ForumThreadService) {
    delete("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))
4
        val success = forumThreadService.deleteForumThreadById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete forumThread with id [$id]"))
    }
}

fun Route.getThreadWithCommentsRoute(commentService: CommentService, forumThreadService: ForumThreadService) {
    get("/threads/{threadId}") {
        val threadId: Int = call.parameters["threadId"]?.toIntOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid thread ID"))

        val thread = forumThreadService.findForumThreadById(threadId)
            ?: return@get call.respond(HttpStatusCode.NotFound, ErrorResponse("ForumThread with id [$threadId] not found"))

        val comments = commentService.findCommentsByThreadId(threadId)

        val response = ForumThreadWithCommentsResponse(
            thread = thread.toForumThreadResponse()!!,
            comments = comments.map(Comment::toCommentResponse)
        )

        call.respond(response)
    }
}







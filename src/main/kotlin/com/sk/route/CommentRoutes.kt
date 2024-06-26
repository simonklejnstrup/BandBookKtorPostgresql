package com.sk.route

import com.sk.model.*
import com.sk.model.comment.Comment
import com.sk.model.comment.CommentRequest
import com.sk.model.comment.CommentResponse
import com.sk.service.CommentService
import com.sk.util.DatabaseFactory
import com.sk.util.getIdParam
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Comment?.toCommentResponse(): CommentResponse? =
    this?.let { CommentResponse(
        it.id!!,
        it.createdBy,
        it.content,
        it.createdAt,
        it.threadId,
    )
    }

fun Application.configureCommentRoutes() {
    routing {
        route("/api/v1/comment") {
            val commentService = CommentService(DatabaseFactory.database)
            createComment(commentService)
            getAllCommentsRoute(commentService)
            getCommentByIdRoute(commentService)
            updateCommentByIdRoute(commentService)
            deleteCommentByIdRoute(commentService)
        }
    }
}

fun Route.createComment(commentService: CommentService) {
    post {
        val request = call.receive<CommentRequest>()

        val success = commentService.createComment(commentRequest = request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create comment"))
    }
}

fun Route.getAllCommentsRoute(commentService: CommentService) {
    get {
        val comments = commentService.findAllComments()
            .map(Comment::toCommentResponse)

        call.respond(message = comments)
    }
}

fun Route.getCommentByIdRoute(commentService: CommentService) {
    get("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        commentService.findCommentById(id)
            ?.let { foundComment -> foundComment.toCommentResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Comment with id [$id] not found"))
    }
}

fun Route.updateCommentByIdRoute(commentService: CommentService) {
    patch("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@patch call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val request = call.receive<CommentRequest>()
        val success = commentService.updateCommentById(id, request)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot update comment with id [$id]"))
    }
}

fun Route.deleteCommentByIdRoute(commentService: CommentService) {
    delete("/{id}") {
        val id: Int = call.parameters.getIdParam()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val success = commentService.deleteCommentById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete comment with id [$id]"))
    }
}


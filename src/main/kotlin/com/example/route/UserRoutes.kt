package com.example.route

import com.example.model.*
import com.example.service.BookService
import com.example.service.UserService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private fun User?.toUserResponse(): UserResponse? =
    this?.let { UserResponse(
        it.id!!,
        it.firstname,
        it.lastname,
        it.email,
//        it.created
    )
    }

fun Application.configureUserRoutes() {
    routing {
        route("/user") {
            val userService = UserService()
            createUser(userService)
//            getAllBooksRoute(bookService)
//            getBookByIdRoute(bookService)
//            updateBookByIdRoute(bookService)
//            deleteBookByIdRoute(bookService)
        }
    }
}

fun Route.createUser(userService: UserService) {
    post {
        val request = call.receive<UserRequest>()

        val success = userService.createUser(userRequest = request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create user"))
    }
}
package com.sk.route

import com.sk.model.*
import com.sk.model.user.User
import com.sk.model.user.UserRequest
import com.sk.model.user.UserResponse
import com.sk.service.UserService
import com.sk.util.DatabaseFactory
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private fun User?.toUserResponse(): UserResponse? =
    this?.let { UserResponse(
        id = it.id!!,
        firstname = it.firstname,
        lastname = it.lastname,
        email = it.email,
        band = it.band,
        created = it.created
    )
    }

fun Application.configureUserRoutes() {
    routing {
        route("/api/v1/user") {
            val userService = UserService(DatabaseFactory.database)
            createUser(userService)
            getAllUsersRoute(userService)
            getUserByIdRoute(userService)
            updateUserByIdRoute(userService)
            deleteUserByIdRoute(userService)
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

fun Route.getAllUsersRoute(userService: UserService) {
    get {
        val users = userService.findAllUsers()
            .map(User::toUserResponse)

        call.respond(message = users)
    }
}

fun Route.getUserByIdRoute(userService: UserService) {
    get("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        userService.findUserById(id)
            ?.let { foundUser -> foundUser.toUserResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("User with id [$id] not found"))
    }
}

fun Route.updateUserByIdRoute(userService: UserService) {
    patch("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@patch call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val request = call.receive<UserRequest>()
        val success = userService.updateUserById(id, request)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot update user with id [$id]"))
    }
}

fun Route.deleteUserByIdRoute(userService: UserService) {
    delete("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val success = userService.deleteUserById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete user with id [$id]"))
    }
}


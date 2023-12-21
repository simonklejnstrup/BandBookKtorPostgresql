package com.example.route

import com.example.model.*
import com.example.model.event.Event
import com.example.model.event.EventRequest
import com.example.model.event.EventResponse
import com.example.service.EventService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

private fun Event?.toEventResponse(): EventResponse? =
    this?.let {
        EventResponse(
            it.id!!,
            it.title,
            it.address,
            it.date,
            it.timeOfGetIn,
            it.timeOfSoundcheck,
            it.timeOfConcert,
            it.timeOfDone,
            it.salaryPerPerson,
            it.costOfRentalGear,
            it.costOfTransport,
            it.extraCosts,
            it.nameOfContactPerson,
            it.telephoneNumberOfContactPerson,
            it.note
        )
    }

fun Application.configureEventRoutes() {
    routing {
        route("/event") {
            val eventService = EventService()
            createEvent(eventService)
            getAllEventsRoute(eventService)
            getEventByIdRoute(eventService)
            updateEventByIdRoute(eventService)
            deleteEventByIdRoute(eventService)
        }
    }
}

fun Route.createEvent(eventService: EventService) {
    post {
        val request = call.receive<EventRequest>()

        val success = eventService.createEvent(request)

        if (success)
            call.respond(HttpStatusCode.Created)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot create event"))
    }
}

fun Route.getAllEventsRoute(eventService: EventService) {
    get {
        val events = eventService.findAllEvents()
            .map(Event::toEventResponse)

        call.respond(message = events)
    }
}

fun Route.getEventByIdRoute(eventService: EventService) {
    get("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        eventService.findEventById(id)
            ?.let { foundEvent -> foundEvent.toEventResponse() }
            ?.let { response -> call.respond(response) }
            ?: return@get call.respond(HttpStatusCode.BadRequest, ErrorResponse("Event with id [$id] not found"))
    }
}

fun Route.updateEventByIdRoute(eventService: EventService) {
    patch("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@patch call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val request = call.receive<EventRequest>()
        val success = eventService.updateEventById(id, request)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot update event with id [$id]"))
    }
}

fun Route.deleteEventByIdRoute(eventService: EventService) {
    delete("/{id}") {
        val id: Long = call.parameters["id"]?.toLongOrNull()
            ?: return@delete call.respond(HttpStatusCode.BadRequest, ErrorResponse("Invalid id"))

        val success = eventService.deleteEventById(id)

        if (success)
            call.respond(HttpStatusCode.NoContent)
        else
            call.respond(HttpStatusCode.BadRequest, ErrorResponse("Cannot delete event with id [$id]"))
    }
}


package com.example.service

import com.example.model.event.Event
import com.example.model.event.EventRequest
import com.example.model.event.Events
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class EventService {

    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5438/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    fun createEvent(eventRequest: EventRequest): Boolean {
        val newEvent = Event {
              title = eventRequest.title
              address = eventRequest.address
              date = eventRequest.date
              timeOfGetIn = eventRequest.timeOfGetIn
              timeOfSoundcheck = eventRequest.timeOfSoundcheck
              timeOfConcert = eventRequest.timeOfConcert
              timeOfDone = eventRequest.timeOfDone
              salaryPerPerson = eventRequest.salaryPerPerson
              costOfRentalGear = eventRequest.costOfRentalGear
              costOfTransport = eventRequest.costOfTransport
              extraCosts = eventRequest.extraCosts
              nameOfContactPerson = eventRequest.nameOfContactPerson
              telephoneNumberOfContactPerson = eventRequest.telephoneNumberOfContactPerson
              note = eventRequest.note
        }

        val affectedRecordsNumber =
            database.sequenceOf(Events).add(newEvent)

        return affectedRecordsNumber == 1
    }

    fun findAllEvents(): Set<Event> =
        database.sequenceOf(Events).toSet()

    fun findEventById(eventId: Long): Event? =
        database.sequenceOf(Events)
            .find { event -> event.id eq eventId }

    fun updateEventById(eventId: Long, eventRequest: EventRequest): Boolean {
        val foundEvent = findEventById(eventId)
        foundEvent?.title = eventRequest.title
        foundEvent?.address = eventRequest.address
        foundEvent?.date = eventRequest.date
        foundEvent?.timeOfGetIn = eventRequest.timeOfGetIn
        foundEvent?.timeOfSoundcheck = eventRequest.timeOfSoundcheck
        foundEvent?.timeOfConcert = eventRequest.timeOfConcert
        foundEvent?.timeOfDone = eventRequest.timeOfDone
        foundEvent?.salaryPerPerson = eventRequest.salaryPerPerson
        foundEvent?.costOfTransport = eventRequest.costOfTransport
        foundEvent?.extraCosts = eventRequest.extraCosts
        foundEvent?.nameOfContactPerson = eventRequest.nameOfContactPerson
        foundEvent?.telephoneNumberOfContactPerson = eventRequest.telephoneNumberOfContactPerson
        foundEvent?.note = eventRequest.note

        val affectedRecordsNumber = foundEvent?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteEventById(eventId: Long): Boolean {
        val foundEvent = findEventById(eventId)

        val affectedRecordsNumber = foundEvent?.delete()

        return affectedRecordsNumber == 1
    }
}

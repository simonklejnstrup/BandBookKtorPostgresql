package com.example.model.event

import org.ktorm.entity.Entity
import org.ktorm.schema.*
import java.time.LocalDate
import java.time.LocalTime

interface Event : Entity<Event> {
    companion object : Entity.Factory<Event>()

    val id: Long?
    var title: String
    var address: String
    var date: LocalDate
    var timeOfGetIn: LocalTime
    var timeOfSoundcheck: LocalTime
    var timeOfConcert: LocalTime
    var timeOfDone: LocalTime
    var salaryPerPerson: Int
    var costOfRentalGear: Int
    var costOfTransport: Int
    var extraCosts: Int
    var nameOfContactPerson: String
    var telephoneNumberOfContactPerson: String
    var note: String
    var type: String
    var lengthOfEachSet: Int
    var numberOfSets: Int
}

object Events : Table<Event>("event") {
    val id = long("id").primaryKey().bindTo(Event::id)
    val title = varchar("title").bindTo(Event::title)
    val address = varchar("address").bindTo(Event::address)
    val date = date("date").bindTo(Event::date)
    val timeOfGetIn = time("time_of_get_in").bindTo(Event::timeOfGetIn)
    val timeOfSoundcheck = time("time_of_soundcheck").bindTo(Event::timeOfSoundcheck)
    val timeOfConcert = time("time_of_concert").bindTo(Event::timeOfConcert)
    val timeOfDone = time("time_of_done").bindTo(Event::timeOfDone)
    val salaryPerPerson = int("salary_per_person").bindTo(Event::salaryPerPerson)
    val costOfRentalGear = int("cost_of_rental_gear").bindTo(Event::costOfRentalGear)
    val costOfTransport = int("cost_of_transport").bindTo(Event::costOfTransport)
    val extraCosts = int("extra_costs").bindTo(Event::extraCosts)
    val nameOfContactPerson = varchar("name_of_contact_person").bindTo(Event::nameOfContactPerson)
    val telephoneNumberOfContactPerson = varchar("telephone_number_of_contact_person").bindTo(Event::telephoneNumberOfContactPerson)
    val note = text("note").bindTo(Event::note)
    val type = text("type").bindTo(Event::type)
    val lengthOfEachSet = int("length_of_each_set").bindTo(Event::lengthOfEachSet)
    val numberOfSets = int("number_of_sets").bindTo(Event::numberOfSets)
}

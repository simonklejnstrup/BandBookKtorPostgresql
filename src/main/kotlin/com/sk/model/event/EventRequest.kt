package com.sk.model.event

import com.sk.util.LocalDateSerializer
import com.sk.util.LocalTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class EventRequest(
    var title: String,
    var address: String,
    @Serializable(with = LocalDateSerializer::class)
    var date: LocalDate,
    @Serializable(with = LocalTimeSerializer::class)
    var timeOfGetIn: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    var timeOfSoundcheck: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    var timeOfConcert: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    var timeOfDone: LocalTime,
    var salaryPerPerson: Int,
    var costOfRentalGear: Int,
    var costOfTransport: Int,
    var extraCosts: Int,
    var nameOfContactPerson: String,
    var telephoneNumberOfContactPerson: String,
    var note: String,
    val type: String,
    val lengthOfEachSet: Int,
    val numberOfSets: Int
)

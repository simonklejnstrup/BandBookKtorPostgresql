package com.sk.model.event

import com.sk.util.LocalDateSerializer
import com.sk.util.LocalTimeSerializer
import kotlinx.serialization.Serializable
import java.time.LocalDate
import java.time.LocalTime

@Serializable
data class EventResponse(
    val id: Long,
    val title: String,
    val address: String,
    @Serializable(with = LocalDateSerializer::class)
    val date: LocalDate,
    @Serializable(with = LocalTimeSerializer::class)
    val timeOfGetIn: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    val timeOfSoundcheck: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    val timeOfConcert: LocalTime,
    @Serializable(with = LocalTimeSerializer::class)
    val timeOfDone: LocalTime,
    val salaryPerPerson: Int,
    val costOfRentalGear: Int,
    val costOfTransport: Int,
    val extraCosts: Int,
    val nameOfContactPerson: String,
    val telephoneNumberOfContactPerson: String,
    val note: String,
    val type: String,
    val lengthOfEachSet: Int,
    val numberOfSets: Int
)

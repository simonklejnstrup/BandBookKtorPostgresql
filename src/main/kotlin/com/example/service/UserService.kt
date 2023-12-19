package com.example.service

import com.example.model.*
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class UserService {

    private val database = Database.connect(
        url = "jdbc:postgresql://localhost:5438/postgres",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "postgres"
    )

    fun createUser(userRequest: UserRequest): Boolean {
        val newUser = User {
            firstname = userRequest.firstname
            lastname = userRequest.lastname
            email = userRequest.email
//            created = userRequest.created
        }

        val affectedRecordsNumber =
            database.sequenceOf(Users).add(newUser)

        return affectedRecordsNumber == 1
    }

    fun findAllUsers(): Set<User> =
        database.sequenceOf(Users).toSet()

    fun findUserById(userId: Long): User? =
        database.sequenceOf(Users)
            .find { user -> user.id eq userId }

    fun updateUserById(userId: Long, userRequest: UserRequest): Boolean {
        val foundUser = findUserById(userId)
        foundUser?.firstname = userRequest.firstname
        foundUser?.lastname = userRequest.lastname
        foundUser?.email = userRequest.email

        val affectedRecordsNumber = foundUser?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteUserById(userId: Long): Boolean {
        val foundUser = findUserById(userId)

        val affectedRecordsNumber = foundUser?.delete()

        return affectedRecordsNumber == 1
    }
}
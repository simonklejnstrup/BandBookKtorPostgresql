package com.sk.service

import com.sk.model.user.User
import com.sk.model.user.UserRequest
import com.sk.model.user.Users
import org.ktorm.database.Database
import org.ktorm.dsl.eq
import org.ktorm.entity.add
import org.ktorm.entity.find
import org.ktorm.entity.sequenceOf
import org.ktorm.entity.toSet

class UserService(private val database: Database) {

    fun createUser(userRequest: UserRequest): Boolean {
        val newUser = User {
            firstname = userRequest.firstname
            lastname = userRequest.lastname
            email = userRequest.email
            band = userRequest.band
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
        foundUser?.band = userRequest.band

        val affectedRecordsNumber = foundUser?.flushChanges()

        return affectedRecordsNumber == 1
    }

    fun deleteUserById(userId: Long): Boolean {
        val foundUser = findUserById(userId)

        val affectedRecordsNumber = foundUser?.delete()

        return affectedRecordsNumber == 1
    }
}
package com.example.service

import com.example.model.User
import com.example.model.BookRequest
import com.example.model.Users
import com.example.model.UserRequest
import org.ktorm.database.Database
import org.ktorm.entity.add
import org.ktorm.entity.sequenceOf

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

}
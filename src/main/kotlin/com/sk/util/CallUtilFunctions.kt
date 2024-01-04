package com.sk.util

import io.ktor.http.*

fun Parameters.getIdParam(): Int? = this["id"]?.toIntOrNull()
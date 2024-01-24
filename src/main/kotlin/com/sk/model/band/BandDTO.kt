package com.sk.model.band

import java.time.Instant

data class BandDTO(
    val id: Int,
    val name: String,
    val createdAt: Instant
)

fun Band.toDTO(): BandDTO = BandDTO(
    id = this.id,
    name = this.name,
    createdAt = this.createdAt
)
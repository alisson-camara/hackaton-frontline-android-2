package com.workshop

import org.jetbrains.exposed.dao.id.EntityID

data class PlayerEntity(
    val name: String,
    val point: String,
    val roomId: EntityID<Int>
)


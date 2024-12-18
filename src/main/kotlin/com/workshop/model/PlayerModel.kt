package com.workshop.model

import org.jetbrains.exposed.dao.id.EntityID

data class PlayerModel(
    val name: String,
    val point: String,
    val roomId: EntityID<Int>
)
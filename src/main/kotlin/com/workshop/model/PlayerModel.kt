package com.workshop.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID

@Serializable
data class PlayerModel(
    val name: String,
    val point: String,
//    val roomId: EntityID<Int>
    val roomId: Int
)
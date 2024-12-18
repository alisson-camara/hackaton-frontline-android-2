package com.workshop.model

import com.workshop.PlayerEntity
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID

@Serializable
data class PlayerModel(
    val name: String,
    val point: String
)

fun PlayerEntity.toModel(): PlayerModel = PlayerModel(
    name = this.name,
    point = this.point
)
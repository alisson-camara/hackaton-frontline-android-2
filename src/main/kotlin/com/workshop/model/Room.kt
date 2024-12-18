package com.workshop.model

import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val name: String,
    val moderator: String,
    val currentTask: String,
    val players: List<PlayerModel>
)

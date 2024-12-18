package com.workshop.model

import com.workshop.db.RoomDAO
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    val name: String,
    val moderator: String,
    val currentTask: String,
    val players: List<PlayerModel> = emptyList()
)

fun RoomDAO.toModel(players: List<PlayerModel>): Room = Room(
    name = this.name,
    moderator = this.moderator,
    currentTask = this.currentTask,
    players = players
)
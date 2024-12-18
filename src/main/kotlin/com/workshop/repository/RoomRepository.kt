package com.workshop.repository

import com.workshop.db.RoomDAO
import com.workshop.model.PlayerModel
import com.workshop.model.Room

class RoomRepository(private val playerRepository: IPlayerRepository) : IRoomRepository {
    override fun create(roomName: String, moderator: String): Room {

        val room = Room(roomName, moderator, "Task 1", emptyList())

        val roomDao = RoomDAO.new {
            this.name = room.name
            this.currentTask = room.currentTask
            this.moderator = room.moderator
        }

        val player = PlayerModel(roomDao.moderator, "?", roomDao.id.value)
        playerRepository.create(player)

        return room.copy(players = listOf(player))
    }
}
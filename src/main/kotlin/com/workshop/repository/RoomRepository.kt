package com.workshop.repository

import com.workshop.db.RoomDAO
import com.workshop.model.PlayerModel
import com.workshop.model.Room

class RoomRepository(private val playerRepository: IPlayerRepository) : IRoomRepository {
    override fun create(roomName: String, moderator: String): Room {
        val room = Room(roomName, "Task 1", moderator)

        val roomDao = RoomDAO.new {
            this.name = room.name
            this.currentTask = room.currentTask
            this.moderator = room.moderator
        }

        val player = PlayerModel(roomDao.moderator, "?", roomDao.id)
        playerRepository.create(player)

        return room
    }
}
package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.db.RoomDAO
import com.workshop.db.RoomTable
import com.workshop.db.suspendTransaction
import com.workshop.model.PlayerModel
import com.workshop.model.Room
import com.workshop.model.toModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RoomRepository(private val playerRepository: IPlayerRepository) : IRoomRepository {
    override suspend fun create(roomName: String, moderator: String): Room {

        val room = Room(roomName, moderator, "Task 1", emptyList())

        val roomDao = suspendTransaction {
            RoomDAO.new {
                this.name = room.name
                this.currentTask = room.currentTask
                this.moderator = room.moderator
            }
        }

        val player = PlayerEntity(name = roomDao.moderator, point =  "?", roomId = roomDao.id)
        playerRepository.create(player)

        return room.copy(players = listOf(player.toModel()))
    }

    override suspend fun get(roomName: String): Room {
        suspendTransaction {
            RoomDAO.find {
                RoomTable.name eq roomName
            }.map{
                name = roomDAO.name,
                moderator = roomDAO.moderator,
                currentTask = roomDAO.currentTask,
                players =
            }
        }
    }
}
package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.db.RoomDAO
import com.workshop.db.RoomTable
import com.workshop.db.suspendTransaction
import com.workshop.model.Room
import com.workshop.model.toModel
import org.jetbrains.exposed.dao.id.EntityID

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

        val player = PlayerEntity(name = roomDao.moderator, point = "?", roomId = roomDao.id)
        playerRepository.create(player)

        return room.copy(players = listOf(player.toModel()))
    }

    override suspend fun getByRoomName(roomName: String): Room? {
        val room = suspendTransaction {
            RoomDAO
                .find { RoomTable.name eq roomName }
                .firstOrNull()
        }
        val players = playerRepository.getByRoomId(room?.id?.value ?: 0)
        return room?.toModel(players)
    }

    override suspend fun join(roomName: String, player: String): Room? {
        val room = suspendTransaction {
            RoomDAO.find { RoomTable.name eq roomName }.firstOrNull()
        }

        val player = PlayerEntity(
            name = player,
            point = "?",
            roomId = room?.id ?: return null
        )

        val player = playerRepository.create(player)
        val roomWithNewPlayer = room.toModel()

        return room.toModel()
    }
}
package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.db.PlayerDAO
import com.workshop.db.PlayerTable
import com.workshop.db.suspendTransaction
import com.workshop.model.PlayerModel
import com.workshop.model.toModel
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.or

class PlayerRepository : IPlayerRepository {
    override suspend fun create(player: PlayerEntity) {
        suspendTransaction {
            PlayerDAO.new {
                name = player.name
                point = player.point
                room = player.roomId
            }
        }
    }

    override suspend fun getByRoomId(roomId: Int): List<PlayerModel> {
        return suspendTransaction {
            PlayerDAO
                .find { PlayerTable.room eq roomId }
                .map(PlayerDAO::toModel)
        }
    }

    override suspend fun removePlayer(player: PlayerEntity, roomId: Int) {
        suspendTransaction {
            PlayerDAO
                .find { PlayerTable.room eq roomId }
        }
    }
}
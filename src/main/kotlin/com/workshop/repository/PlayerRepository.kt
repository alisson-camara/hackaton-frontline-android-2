package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.db.PlayerDAO
import com.workshop.db.PlayerTable
import com.workshop.db.suspendTransaction
import com.workshop.model.PlayerModel
import com.workshop.model.toModel
import org.jetbrains.exposed.dao.id.EntityID

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
}
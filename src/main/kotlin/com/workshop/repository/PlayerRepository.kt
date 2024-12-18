package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.db.PlayerDAO
import com.workshop.model.PlayerModel
import org.jetbrains.exposed.dao.id.EntityID

class PlayerRepository : IPlayerRepository {
    override suspend fun create(player: PlayerEntity) {
        PlayerDAO.new {
            name = player.name
            point = player.point
            room = player.roomId
        }
    }
}
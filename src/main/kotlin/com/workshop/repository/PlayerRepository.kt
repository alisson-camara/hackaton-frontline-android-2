package com.workshop.repository

import com.workshop.db.PlayerDAO
import com.workshop.model.PlayerModel

class PlayerRepository() : IPlayerRepository {
    override fun create(playerModel: PlayerModel) {
        PlayerDAO.new {
            name = playerModel.name
            point = playerModel.point
            room = playerModel.roomId
        }
    }
}
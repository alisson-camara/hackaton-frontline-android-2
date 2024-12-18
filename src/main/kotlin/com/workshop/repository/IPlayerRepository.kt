package com.workshop.repository

import com.workshop.model.PlayerModel

interface IPlayerRepository {
    fun create(playerModel: PlayerModel)
}
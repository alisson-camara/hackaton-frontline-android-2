package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.model.PlayerModel

interface IPlayerRepository {
    suspend fun create(player: PlayerEntity)
}
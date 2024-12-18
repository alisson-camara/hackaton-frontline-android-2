package com.workshop.repository

import com.workshop.PlayerEntity
import com.workshop.model.PlayerModel

interface IPlayerRepository {
    suspend fun create(player: PlayerEntity)
    suspend fun getByRoomId(roomId: Int): List<PlayerModel>
    suspend fun removePlayer(player: PlayerEntity, roomId: Int)
}
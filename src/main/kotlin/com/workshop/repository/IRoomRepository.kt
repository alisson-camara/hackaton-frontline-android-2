package com.workshop.repository

import com.workshop.model.Room

interface IRoomRepository {
    suspend fun create(roomName: String, moderator: String): Room
    suspend fun getByRoomName(roomName: String): Room?
    suspend fun join(roomName: String, player: String): Room?
    suspend fun removePlayer(playerName: String, roomName: String)
}
package com.workshop.repository

import com.workshop.model.Room

interface IRoomRepository {
    suspend fun create(roomName: String, moderator: String): Room
}
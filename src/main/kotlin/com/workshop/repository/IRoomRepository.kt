package com.workshop.repository

import com.workshop.model.Room

interface IRoomRepository {
    fun create(roomName: String, moderator: String): Room
}
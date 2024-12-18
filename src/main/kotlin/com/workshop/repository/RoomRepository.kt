package com.workshop.repository

import com.workshop.db.RoomDAO
import com.workshop.model.Room

class RoomRepository : IRoomRepository {
    override fun create(room: Room) {
        RoomDAO.new {
            name = room.name
            currentTask = room.currentTask
            moderator = room.moderator
        }
    }
}
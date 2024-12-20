package com.workshop

import com.workshop.model.FakeTaskRepository
import com.workshop.model.PostgresTaskRepository
import com.workshop.repository.PlayerRepository
import com.workshop.repository.RoomRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val taskRepository = PostgresTaskRepository()
    val playerRepository = PlayerRepository()
    val roomRepository = RoomRepository(playerRepository)

    configureDatabases()
    configureSerialization(taskRepository)
    configureRouting(roomRepository)

}

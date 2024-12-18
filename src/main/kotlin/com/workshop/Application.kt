package com.workshop

import com.workshop.model.FakeTaskRepository
import com.workshop.model.PostgresTaskRepository
import com.workshop.repository.RoomRepository
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    //val repository = PostgresTaskRepository()
    val taskRepository = FakeTaskRepository()
    val roomRepository = RoomRepository()
    configureSerialization(taskRepository)
    //configureDatabases()
    configureRouting(roomRepository)
}

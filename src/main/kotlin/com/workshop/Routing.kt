package com.workshop

import com.workshop.repository.IRoomRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.http.content.staticResources
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.routing

fun Application.configureRouting(roomRepository: IRoomRepository) {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    routing {

        get("/") {
            call.respondText("Hello World!")
        }

        post("/create-room") {
            val moderator = call.parameters["moderator"] ?: ""
            val roomName = call.parameters["room"] ?: ""

            val room = roomRepository.create(roomName, moderator)
            call.respond(room)
        }

        post("/join-room") {
            val player = call.parameters["player"] ?: ""
            val roomName = call.parameters["room"] ?: ""

            val room = roomRepository.join(roomName, player)
            room?.let { room ->
                call.respond(room)
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        get("/room") {
            val roomName = call.parameters["room"] ?: ""
            val room = roomRepository.getByRoomName(roomName)

            room?.let { room ->
                call.respond(room)
            } ?: call.respond(HttpStatusCode.NotFound)
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
    }
}

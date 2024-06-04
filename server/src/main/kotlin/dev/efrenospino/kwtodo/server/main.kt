package dev.efrenospino.kwtodo.server

import dev.efrenospino.kwtodo.models.Task
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}


fun Application.module() {
    configureSerialization()
    configureHTTP()
    configureRouting()
}


fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json()
    }
    routing {
        get("/json/kotlinx-serialization") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}

fun Application.configureHTTP() {
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)
        allowHeader(HttpHeaders.Authorization)
        allowHeader("MyCustomHeader")
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }
}

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/v1/tasks") {
            call.respond(
                listOf(
                    Task(
                        id = 1,
                        name = "Buy groceries",
                        completed = false,
                        createdAt = "2024-05-01T10:15:30",
                        updatedAt = "2024-05-01T10:15:30"
                    ),
                    Task(
                        id = 2,
                        name = "Walk the dog",
                        completed = true,
                        createdAt = "2024-05-02T08:00:00",
                        updatedAt = "2024-05-02T09:00:00"
                    ),
                    Task(
                        id = 3,
                        name = "Read a book",
                        completed = false,
                        createdAt = "2024-05-03T14:30:00",
                        updatedAt = "2024-05-03T15:00:00"
                    ),
                    Task(
                        id = 4,
                        name = "Write Kotlin code",
                        completed = true,
                        createdAt = "2024-05-04T11:00:00",
                        updatedAt = "2024-05-04T12:30:00"
                    ),
                    Task(
                        id = 5,
                        name = "Exercise",
                        completed = false,
                        createdAt = "2024-05-05T07:00:00",
                        updatedAt = "2024-05-05T08:00:00"
                    )
                )
            )
        }
    }
}

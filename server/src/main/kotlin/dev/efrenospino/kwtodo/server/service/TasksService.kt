package dev.efrenospino.kwtodo.server.service

import dev.efrenospino.kwtodo.models.api.TaskUpdate
import dev.efrenospino.kwtodo.server.data.TasksRepository
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun RootRoute.tasks() {
    get("/v1/tasks") {
        call.respond(
            HttpStatusCode.OK,
            TasksRepository.getAll(call.queryParameters["search"] ?: "")
        )
    }
    post("/v1/tasks") {
        val formParameters = call.receiveParameters()
        call.checkParam(formParameters["name"]) { taskName ->
            call.respond(
                HttpStatusCode.Created,
                TasksRepository.create(taskName)
            )
        }
    }
    patch("/v1/tasks/{id}") {
        call.checkParam(call.pathParameters["id"]) { taskId ->
            val taskUpdate = call.receive<TaskUpdate>()
            call.respond(
                HttpStatusCode.OK,
                TasksRepository.update(
                    taskId = taskId.toLong(),
                    name = taskUpdate.name,
                    completed = taskUpdate.completed
                )
            )
        }
    }
    delete("/v1/tasks/{id}") {
        call.checkParam(call.pathParameters["id"]) { taskId ->
            call.respond(
                HttpStatusCode.OK,
                TasksRepository.delete(taskId.toLong())
            )
        }
    }
}

suspend fun RoutingCall.checkParam(paramValue: String?, call: suspend (String) -> Unit) {
    if (paramValue == null) {
        respond(HttpStatusCode.BadRequest)
    } else {
        call(paramValue)
    }
}
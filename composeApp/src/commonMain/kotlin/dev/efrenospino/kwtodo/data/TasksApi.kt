package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.BuildKonfig
import dev.efrenospino.kwtodo.models.Task
import dev.efrenospino.kwtodo.models.api.TaskUpdate
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

object TasksApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        defaultRequest {
            url(BuildKonfig.API_URL)
        }
    }

    suspend fun getAllTasks(search: String): List<Task> {
        return client.get("/v1/tasks?search=$search").body<List<Task>>()
            .sortedBy { it.id }
    }

    suspend fun create(name: String): List<Task> {
        return client.submitForm(
            "/v1/tasks",
            formParameters = Parameters.build {
                append("name", name)
            }
        ) {
            accept(ContentType.Any)
        }.body<List<Task>>()
    }

    suspend fun update(task: Task): List<Task> {
        return client.patch("/v1/tasks/${task.id}") {
            accept(ContentType.Application.Json)
            contentType(ContentType.Application.Json)
            setBody(TaskUpdate(task.name, task.completed))
        }.body<List<Task>>()
    }

    suspend fun delete(task: Task): List<Task> {
        return client.delete("/v1/tasks/${task.id}").body<List<Task>>()
    }
}
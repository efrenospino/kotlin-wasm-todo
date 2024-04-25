package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.BuildKonfig
import dev.efrenospino.kwtodo.domain.Task
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.datetime.Clock
import kotlinx.serialization.json.Json

class WasmTasksApi : TasksApi {

    private val client = HttpClient {
        defaultRequest {
            headers {
                append("apiKey", BuildKonfig.SUPABASE_KEY)
            }
        }
        install(ContentNegotiation) {
            json(Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getAllTasks(search: String): List<Task> {
        return client.get("${BuildKonfig.SUPABASE_URL}/rest/v1/tasks?select=*").body<List<Task>>()
    }

    override suspend fun save(name: String): Task {
        return client.post("${BuildKonfig.SUPABASE_URL}/rest/v1/tasks") {
            headers {
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Prefer, "return=representation")
            }
            setBody(
                Task(
                    name = name,
                    createdAt = Clock.System.now().toString(),
                    updatedAt = Clock.System.now().toString(),
                )
            )
        }.body<List<Task>>().first()
    }

    override suspend fun save(task: Task): Task {
        return client.patch("${BuildKonfig.SUPABASE_URL}/rest/v1/tasks?id=eq.${task.id}") {
            headers {
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Prefer, "return=representation")
            }
            setBody(
                task.copy(updatedAt = Clock.System.now().toString())
            )
        }.body<List<Task>>().first()
    }

    override suspend fun delete(task: Task): Task {
        return client.delete("${BuildKonfig.SUPABASE_URL}/rest/v1/tasks?id=eq.${task.id}") {
            headers {
                append(HttpHeaders.ContentType, "application/json")
                append(HttpHeaders.Prefer, "return=representation")
            }
        }.body<List<Task>>().first()
    }

}
package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.BuildKonfig
import dev.efrenospino.kwtodo.domain.Task
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.datetime.Clock

class WasmTasksApi : TasksApi {

    private val supabase = createSupabaseClient(
        supabaseUrl = BuildKonfig.SUPABASE_URL,
        supabaseKey = BuildKonfig.SUPABASE_KEY
    ) {
        install(Postgrest)
    }

    override suspend fun getAllTasks(search: String): List<Task> {
        return supabase.from("tasks").select {
            order(column = "id", order = Order.ASCENDING)
            filter {
                Task::name ilike "%$search%"
            }
        }.decodeList<Task>()
    }

    override suspend fun save(name: String): Task {
        val newTask = Task(
            name = name,
            createdAt = Clock.System.now().toString(),
            updatedAt = Clock.System.now().toString(),
        )
        return supabase.from("tasks").insert(newTask) {
            select()
        }.decodeSingle<Task>()
    }

    override suspend fun save(task: Task): Task {
        return supabase.from("tasks").update({
            Task::name setTo task.name
            Task::completed setTo task.completed
            Task::updatedAt setTo Clock.System.now().toString()
        }) {
            select()
            filter {
                Task::id eq task.id
            }
        }.decodeSingle()
    }

    override suspend fun delete(task: Task): Task {
        return supabase.from("tasks").delete {
            select()
            filter {
                Task::id eq task.id
            }
        }.decodeSingle<Task>()
    }

}
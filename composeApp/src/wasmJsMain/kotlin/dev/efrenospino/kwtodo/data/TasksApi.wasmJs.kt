package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.BuildKonfig
import dev.efrenospino.kwtodo.domain.Task
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from

class WasmTasksApi : TasksApi {

    private val supabase = createSupabaseClient(
        supabaseUrl = BuildKonfig.SUPABASE_URL,
        supabaseKey = BuildKonfig.SUPABASE_KEY
    ) {
        install(Postgrest)
    }

    override suspend fun getAllTasks(search: String): List<Task> {
        return supabase.from("tasks").select {
            filter {
                Task::name ilike "%$search%"
            }
        }.decodeList<Task>()
    }
}
package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Task

class WasmTasksApi : TasksApi {
    override suspend fun getAllTasks(): List<Task> {
        return emptyList()
    }
}
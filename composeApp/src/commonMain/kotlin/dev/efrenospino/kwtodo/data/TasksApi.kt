package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Task

interface TasksApi {
    suspend fun getAllTasks(): List<Task>
}
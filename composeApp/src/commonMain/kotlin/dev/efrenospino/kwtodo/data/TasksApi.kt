package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Task

interface TasksApi {
    suspend fun getAllTasks(search: String): List<Task>
    suspend fun save(name: String): Task
    suspend fun save(task: Task): Task
    suspend fun delete(task: Task): Task
}
package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Task

class TasksRepository(private val tasksApi: TasksApi) {

    suspend fun getAllTasks(): List<Task> {
        return tasksApi.getAllTasks()
    }
    
}
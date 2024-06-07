package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.models.Task

class TasksRepository(private val tasksApi: TasksApi) {

    suspend fun getAllTasks(search: String): List<Task> {
        return tasksApi.getAllTasks(search)
    }

    suspend fun newTask(name: String): List<Task> {
        return tasksApi.create(name)
    }

    suspend fun completeTask(task: Task, completed: Boolean): List<Task> {
        return tasksApi.update(task.copy(completed = completed))
    }

    suspend fun deleteTask(task: Task): List<Task> {
        return tasksApi.delete(task)
    }

    suspend fun updateTask(task: Task, name: String): List<Task> {
        return tasksApi.update(task.copy(name = name))
    }

}
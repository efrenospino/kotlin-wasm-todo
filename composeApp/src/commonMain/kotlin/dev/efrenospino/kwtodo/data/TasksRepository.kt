package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Task

class TasksRepository(private val tasksApi: TasksApi) {

    suspend fun getAllTasks(search: String): List<Task> {
        return tasksApi.getAllTasks(search)
    }

    suspend fun newTask(name: String): Task {
        return tasksApi.save(name)
    }

    suspend fun completeTask(task: Task, completed: Boolean): Task {
        return tasksApi.save(task.copy(completed = completed))
    }

    suspend fun deleteTask(task: Task): Task {
        return tasksApi.delete(task)
    }

    suspend fun updateTask(task: Task, name: String): Task {
        return tasksApi.save(task.copy(name = name))
    }

}
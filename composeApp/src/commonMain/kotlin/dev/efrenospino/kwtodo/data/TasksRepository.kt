package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Status
import dev.efrenospino.kwtodo.domain.Task
import kotlinx.datetime.Clock

class TasksRepository {

    val allTasks = Tasks.list

    fun createNewTaskWith(name: String) {
        Tasks.create(
            Task(
                id = Tasks.nextId(),
                name = name,
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
        )
    }

    fun updateTask(name: String) {
        // TODO
    }

    fun completeTask(task: Task) {
        Tasks.edit(
            task.copy(status = Status.DONE)
        )
    }

    fun unCompleteTask(task: Task) {
        Tasks.edit(
            task.copy(status = Status.CREATED)
        )
    }

    fun deleteTask(task: Task) {
        Tasks.delete(task)
    }
}
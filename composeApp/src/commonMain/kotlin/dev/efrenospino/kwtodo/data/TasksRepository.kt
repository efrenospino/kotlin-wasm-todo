package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Status
import dev.efrenospino.kwtodo.domain.Task
import kotlinx.datetime.Clock

class TasksRepository {

    fun getAllTasks(): List<Task> {
        return listOf(
            Task(
                id = 1,
                name = "Buy groceries",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 2,
                name = "Go to the doctor",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 3,
                name = "Eat dinner out",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 4,
                name = "Go to the doctor",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 5,
                name = "Go to the doctor",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 6,
                name = "Go to the doctor",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
            Task(
                id = 7,
                name = "Go to the doctor",
                status = Status.CREATED,
                createdAt = Clock.System.now(),
                updatedAt = Clock.System.now()
            ),
        )
    }
}
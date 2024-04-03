package dev.efrenospino.kwtodo.data

import dev.efrenospino.kwtodo.domain.Status
import dev.efrenospino.kwtodo.domain.Task
import kotlinx.datetime.Clock

object Tasks {

    val list: MutableList<Task> = mutableListOf(
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
        )
    )

    fun create(task: Task) {
        list.add(task)
    }

    fun delete(task: Task) {
        list.remove(task)
    }

    fun edit(task: Task) {
        list[list.indexOf(task)] = task
    }

    fun nextId(): Int = list.maxBy { it.id }.id + 1
}
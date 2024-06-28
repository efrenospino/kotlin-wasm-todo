package dev.efrenospino.kwtodo.server.util

import dev.efrenospino.kwtodo.models.Task

fun mapTaskToSharedModel(id: Long, name: String, completed: Long, createdAt: String, updatedAt: String): Task = Task(
    id.toInt(),
    name,
    completed == 1L,
    createdAt,
    updatedAt
)
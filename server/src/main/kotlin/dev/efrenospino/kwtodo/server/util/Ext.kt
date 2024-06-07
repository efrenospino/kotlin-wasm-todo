package dev.efrenospino.kwtodo.server.util

import dev.efrenospino.kwtodo.database.TasksDatabase
import dev.efrenospino.kwtodo.models.Task
import dev.efrenospino.kwtodo.server.data.TasksRepository.getAll

fun Boolean?.toLong(): Long? {
    return when (this) {
        true -> 1L
        false -> 0L
        null -> null
    }
}

fun TasksDatabase.singleTransactionWithResult(runTransaction: () -> Unit): List<Task> {
    return transactionWithResult {
        runTransaction()
        getAll()
    }
}


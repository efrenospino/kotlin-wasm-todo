package dev.efrenospino.kwtodo.server.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import dev.efrenospino.kwtodo.models.Task
import dev.efrenospino.kwtodo.server.db.TasksDB

object TasksRepository {

    private val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    private val tasksDB: TasksDB = TasksDB(driver)

    init {
        TasksDB.Schema.create(driver)
    }

    fun getAll(search: String = ""): List<Task> {
        return tasksDB.taskQueries.selectAll(search, ::mapToSharedModel).executeAsList()
    }

    fun create(name: String): List<Task> {
        return tasksDB.singleTransactionWithResult {
            tasksDB.taskQueries.insert(name)
        }
    }

    fun update(taskId: Long, name: String?, completed: Boolean?): List<Task> {
        return tasksDB.singleTransactionWithResult {
            val task = tasksDB.taskQueries.selectOne(id = taskId).executeAsOne()
            val updatedTask = task.copy(
                name = name ?: task.name,
                completed = completed?.toLong() ?: task.completed
            )
            tasksDB.taskQueries.updateById(
                id = taskId,
                name = updatedTask.name,
                completed = updatedTask.completed
            )
        }
    }

    fun delete(taskId: Long): List<Task> {
        return tasksDB.singleTransactionWithResult {
            tasksDB.taskQueries.deleteById(taskId)
        }
    }

    private fun TasksDB.singleTransactionWithResult(runTransaction: () -> Unit): List<Task> {
        return transactionWithResult {
            runTransaction()
            getAll()
        }
    }

    private fun mapToSharedModel(
        id: Long,
        name: String,
        completed: Long,
        createdAt: String,
        updatedAt: String,
    ): Task = Task(
        id.toInt(), name, completed == 1L, createdAt, updatedAt
    )
}

private fun Boolean?.toLong(): Long? {
    return when (this) {
        true -> 1L
        false -> 0L
        null -> null
    }
}

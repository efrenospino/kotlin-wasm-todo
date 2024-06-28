package dev.efrenospino.kwtodo.server.data

import app.cash.sqldelight.db.SqlDriver
import dev.efrenospino.kwtodo.database.TasksDatabase
import dev.efrenospino.kwtodo.database.TasksDatabaseDriver
import dev.efrenospino.kwtodo.models.Task
import dev.efrenospino.kwtodo.server.util.mapTaskToSharedModel
import dev.efrenospino.kwtodo.server.util.singleTransactionWithResult
import dev.efrenospino.kwtodo.server.util.toLong

class TasksRepository(filePath: String?) {
    private val driver: SqlDriver = TasksDatabaseDriver.instance(filePath = filePath)
    private val tasksDB: TasksDatabase = TasksDatabase(driver)

    init {
        TasksDatabase.Schema.create(driver)
    }

    fun getAll(search: String = ""): List<Task> {
        return tasksDB.taskQueries.selectAll(search, ::mapTaskToSharedModel).executeAsList()
    }

    fun create(name: String): List<Task> {
        return tasksDB.singleTransactionWithResult {
            tasksDB.taskQueries.insert(name)
        }
    }

    fun update(taskId: Long, name: String?, completed: Boolean?): List<Task> {
        return tasksDB.singleTransactionWithResult {
            val task = tasksDB.taskQueries.selectOne(id = taskId).executeAsOne()
            val updatedTask =
                task.copy(
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
}
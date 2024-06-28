package dev.efrenospino.kwtodo.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

object TasksDatabaseDriver {
    fun instance(filePath: String?): SqlDriver {
        return JdbcSqliteDriver("jdbc:sqlite:${filePath ?: ""}kwtodo.db")
    }
}
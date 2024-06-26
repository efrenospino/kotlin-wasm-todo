package dev.efrenospino.kwtodo.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver

object TasksDatabaseDriver {
    fun instance(path: String = "jdbc:sqlite:kwtodo.db"): SqlDriver {
        return JdbcSqliteDriver(path)
    }
}
package dev.efrenospino.kwtodo

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import dev.efrenospino.kwtodo.data.TasksApi
import dev.efrenospino.kwtodo.data.TasksRepository
import dev.efrenospino.kwtodo.ui.Home

@Composable
fun App() {
    MaterialTheme {
        Home(tasksRepository = TasksRepository(tasksApi = TasksApi))
    }
}
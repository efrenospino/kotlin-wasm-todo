package dev.efrenospino.kwtodo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.efrenospino.kwtodo.data.TasksRepository
import dev.efrenospino.kwtodo.models.Task
import dev.efrenospino.kwtodo.ui.components.*
import kotlinx.coroutines.launch

@Composable
fun Home(tasksRepository: TasksRepository) {

    var allTasks by remember { mutableStateOf(emptyList<Task>()) }
    var editableTask: Task? by remember { mutableStateOf(null) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        allTasks = tasksRepository.getAllTasks(search = "")
    }

    Scaffold(topBar = {
        TopAppBar {
            Text(
                text = "To-do List App",
                modifier = Modifier.fillMaxWidth().wrapContentWidth(align = Alignment.CenterHorizontally)
            )
        }
    }) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues).fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchBox {
                coroutineScope.launch {
                    allTasks = tasksRepository.getAllTasks(search = it)
                }
            }

            LazyColumn(
                modifier = Modifier.padding(10.dp).fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                items(allTasks) { task ->
                    if (editableTask == task) {
                        EditableTaskCard(text = task.name) { name ->
                            SaveButton {
                                coroutineScope.launch {
                                    tasksRepository.updateTask(task, name).let { updatedTasks ->
                                        allTasks = updatedTasks
                                    }
                                }
                            }
                            CancelButton {
                                editableTask = null
                            }
                        }
                    } else {
                        TaskCard(
                            task = task,
                            onEditClick = {
                                editableTask = task
                            },
                            onCheckboxClick = { completed ->
                                coroutineScope.launch {
                                    tasksRepository.completeTask(task, completed).let { updatedTasks ->
                                        allTasks = updatedTasks
                                    }
                                }
                            },
                            onDeleteClick = {
                                coroutineScope.launch {
                                    tasksRepository.deleteTask(task).let { updatedTasks ->
                                        allTasks = updatedTasks
                                    }
                                }
                            }
                        )
                    }
                }

                if (allTasks.isEmpty()) {
                    item {
                        Text("No results")
                    }
                }

                item {
                    EditableTaskCard {
                        CreateButton {
                            coroutineScope.launch {
                                tasksRepository.newTask(it).let { updatedTasks ->
                                    allTasks = updatedTasks
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


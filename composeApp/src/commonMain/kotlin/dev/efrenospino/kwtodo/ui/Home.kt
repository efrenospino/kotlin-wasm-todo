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
import dev.efrenospino.kwtodo.domain.Task
import dev.efrenospino.kwtodo.ui.components.*

@Composable
fun Home(tasksRepository: TasksRepository) {

    val allTasks = remember { tasksRepository.allTasks }
    var taskBeingEdited: Task? by remember { mutableStateOf(null) }

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

            SearchBox()

            LazyColumn(
                modifier = Modifier.padding(10.dp).fillMaxHeight(), verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {

                items(allTasks) { task ->
                    if (taskBeingEdited == task) {
                        EditableTaskCard(text = task.name) {
                            SaveButton {
                                tasksRepository.updateTask(it)
                            }
                            CancelButton {
                                taskBeingEdited = null
                            }
                        }
                    } else {
                        TaskCard(
                            task = task,
                            onEditClick = {
                                taskBeingEdited = task
                            }, onDeleteClick = {
                                tasksRepository.deleteTask(task)
                            }
                        )
                    }
                }

                item {
                    EditableTaskCard {
                        CreateButton {
                            tasksRepository.createNewTaskWith(it)
                        }
                    }
                }
            }
        }
    }
}



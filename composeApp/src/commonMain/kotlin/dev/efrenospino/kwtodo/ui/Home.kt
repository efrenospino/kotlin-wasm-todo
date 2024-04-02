package dev.efrenospino.kwtodo.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.efrenospino.kwtodo.data.TasksRepository
import dev.efrenospino.kwtodo.ui.components.NewTaskCard
import dev.efrenospino.kwtodo.ui.components.SearchBox
import dev.efrenospino.kwtodo.ui.components.TaskCard

@Composable
fun Home(tasksRepository: TasksRepository) {
    Scaffold(
        topBar = {
            TopAppBar {
                Text(
                    text = "To-do List App",
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier.padding(paddingValues)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            SearchBox()

            LazyColumn(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                items(tasksRepository.getAllTasks()) { task ->
                    TaskCard(task)
                }

                item {
                    NewTaskCard()
                }
            }
        }
    }
}



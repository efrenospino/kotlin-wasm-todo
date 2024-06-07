package dev.efrenospino.kwtodo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.efrenospino.kwtodo.models.Task

@Composable
fun EditableTaskCard(text: String = "", action: @Composable RowScope.(String) -> Unit = {}) {

    var newTaskText by remember { mutableStateOf(text) }

    Card(Modifier.fillMaxWidth()) {
        Row(
            Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = newTaskText,
                    onValueChange = {
                        newTaskText = it
                    },
                    placeholder = {
                        Text("Type something...")
                    }
                )
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.End),
            ) {
                action(newTaskText)
            }
        }
    }
}

@Composable
fun TaskCard(
    task: Task,
    onEditClick: () -> Unit = {},
    onCheckboxClick: (Boolean) -> Unit = {},
    onDeleteClick: () -> Unit = {},
) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = task.completed, onCheckedChange = onCheckboxClick)
                Text(text = task.name)
            }
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.End),
            ) {
                Button(
                    onClick = onEditClick, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ) {
                    Text("EDIT", color = Color.White)
                }
                Button(
                    onClick = onDeleteClick, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("DELETE", color = Color.White)
                }
            }
        }
    }
}

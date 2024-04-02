package dev.efrenospino.kwtodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NewTaskCard() {

    var newTaskText by remember { mutableStateOf("") }

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
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                ) {
                    Text("CREATE", color = Color.White)
                }
            }
        }
    }
}
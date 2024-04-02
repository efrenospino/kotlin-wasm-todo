package dev.efrenospino.kwtodo.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.efrenospino.kwtodo.domain.Status
import dev.efrenospino.kwtodo.domain.Task

@Composable
fun TaskCard(task: Task) {
    Card(Modifier.fillMaxWidth()) {
        Row(Modifier.fillMaxWidth().padding(10.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(checked = task.status == Status.DONE, onCheckedChange = {})
                Text(text = task.name)
            }
            Row(
                modifier = Modifier.weight(1F),
                horizontalArrangement = Arrangement.spacedBy(space = 10.dp, alignment = Alignment.End),
            ) {
                Button(
                    onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
                ) {
                    Text("EDIT", color = Color.White)
                }
                Button(
                    onClick = {}, colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                ) {
                    Text("DELETE", color = Color.White)
                }
            }
        }
    }
}

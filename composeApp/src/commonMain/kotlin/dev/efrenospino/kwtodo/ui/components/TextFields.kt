package dev.efrenospino.kwtodo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchBox() {
    var searchTaskText by remember { mutableStateOf("") }
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        maxLines = 1,
        value = searchTaskText,
        onValueChange = {
            searchTaskText = it
        },
        placeholder = {
            Text("Search")
        }
    )
}
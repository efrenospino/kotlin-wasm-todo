package dev.efrenospino.kwtodo.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SearchBox(onValueChange: (String) -> Unit = {}) {
    var searchTaskText by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    var searchJob: Job? = null

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth().padding(20.dp),
        maxLines = 1,
        value = searchTaskText,
        onValueChange = {
            searchTaskText = it
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
                delay(500)
                onValueChange(searchTaskText)
            }
        },
        placeholder = {
            Text("Search")
        },
        trailingIcon = {
            if (searchTaskText.isNotEmpty()) {
                Icon(
                    imageVector = Icons.Rounded.Clear,
                    contentDescription = "Clear",
                    modifier = Modifier.clickable {
                        searchTaskText = ""
                        searchJob?.cancel()
                        searchJob = coroutineScope.launch {
                            delay(500)
                            onValueChange(searchTaskText)
                        }
                    }
                )
            }
        }
    )
}
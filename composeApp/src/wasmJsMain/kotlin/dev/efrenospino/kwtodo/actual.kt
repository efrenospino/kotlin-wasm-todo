package dev.efrenospino.kwtodo

import dev.efrenospino.kwtodo.data.TasksApi
import dev.efrenospino.kwtodo.data.WasmTasksApi

actual fun getTasksApi(): TasksApi {
    return WasmTasksApi()
}
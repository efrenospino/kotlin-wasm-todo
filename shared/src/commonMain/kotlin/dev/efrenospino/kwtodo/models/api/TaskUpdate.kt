package dev.efrenospino.kwtodo.models.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TaskUpdate(
    @SerialName("name") var name: String? = null,
    @SerialName("completed") var completed: Boolean? = null,
)

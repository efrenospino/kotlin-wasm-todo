package dev.efrenospino.kwtodo.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    @SerialName("id") val id: Int? = null,
    @SerialName("name") val name: String,
    @SerialName("completed") val completed: Boolean,
    @SerialName("created_at") val createdAt: String,
    @SerialName("updated_at") val updatedAt: String,
)
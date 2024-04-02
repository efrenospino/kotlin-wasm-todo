package dev.efrenospino.kwtodo.domain

import kotlinx.datetime.Instant

data class Task(
    val id: Int,
    val name: String,
    val status: Status,
    val createdAt: Instant,
    val updatedAt: Instant,
)
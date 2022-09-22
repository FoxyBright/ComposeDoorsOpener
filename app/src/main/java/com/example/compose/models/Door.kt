package com.example.compose.models

data class Door(
    val name: String,
    val snapshot: String?,
    val room: String,
    val favorite: Boolean,
    val id: Int
)
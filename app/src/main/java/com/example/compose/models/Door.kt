package com.example.compose.models

data class Door(
    val id: Int,
    val name: String,
    val snapshot: String?,
    val room: String?,
    val favorites: Boolean
)
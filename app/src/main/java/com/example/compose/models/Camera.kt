package com.example.compose.models

data class Camera(
    val name: String,
    val snapshot: String,
    val room: String,
    val favorite: Boolean,
    val rec: Boolean,
    val id: Int
)
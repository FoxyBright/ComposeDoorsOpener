package com.example.compose.models

data class Camera(
    val name: String,
    val snapshot: String,
    var room: String,
    val favorites: Boolean,
    val rec: Boolean,
    val id: Int
)
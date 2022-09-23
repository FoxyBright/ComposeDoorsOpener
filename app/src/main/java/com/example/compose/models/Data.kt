package com.example.compose.models

class Data {
    val cameras: List<Camera>? = null
        get() {
            for (camera in field!!) if (camera.room == null) camera.room = "Вне комнат"
            return field
        }
}
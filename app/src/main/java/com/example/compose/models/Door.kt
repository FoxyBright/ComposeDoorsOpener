package com.example.compose.models

import com.example.compose.R

class Door(
    var name: String = "",
    var image: Int = 0,
    var room: String = "",
    var favorite: Boolean = false,
    var id: Int = 0
) {
    fun getDoors(): List<Door> {
        return listOf(
            Door("Кабинет"),
            Door("Зал"),
            Door("Веранда"),
            Door("Домофон", R.drawable.test_img)
        )
    }
}
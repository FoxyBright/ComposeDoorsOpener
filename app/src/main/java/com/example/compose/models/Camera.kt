package com.example.compose.models

import com.example.compose.R

class Camera(
    var name: String = "",
    var image: Int = 0,
    var room: String = "",
    var favorite: Boolean = false,
    var rec: Boolean = false,
    var id: Int = 0
){
    fun getCameras(): List<Camera> {
        return listOf(
            Camera("Камера 1", R.drawable.test_img, "Кабинет", true, rec = false),
            Camera("Камера 2", R.drawable.test_img, "Зал", true, rec = false),
            Camera("Камера 3", R.drawable.test_img, "Веранда", false, rec = true),
            Camera("Камера 4", R.drawable.test_img, "Домофон", true, rec = false)
        )
    }
}
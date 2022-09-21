package com.example.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.models.Camera
import com.example.compose.ui.theme.AppBackground

@Composable
fun CamerasScreen(cameras: List<Camera>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(AppBackground)
    ) {
        itemsIndexed(cameras) { _, camera ->
            CameraItem(camera)
        }
    }
}

@Preview
@Composable
fun CamerasScreenPreview(){
    val cameras = listOf(
        Camera("Камера 1", R.drawable.test_img, "Кабинет", true, rec = false),
        Camera("Камера 2", R.drawable.test_img, "Зал", false, rec = true),
        Camera("Камера 3", R.drawable.test_img, "Веранда", true, rec = true),
        Camera("Камера 4", R.drawable.test_img, "Домофон", false, rec = false)
    )
    CamerasScreen(cameras)
}
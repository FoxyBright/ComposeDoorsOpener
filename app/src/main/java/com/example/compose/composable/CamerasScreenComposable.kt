package com.example.compose.composable

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
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun CamerasScreen(cameras: List<Camera>) {
    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(AppBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(cameras) { _, camera ->
            CameraItem(camera)
        }
    }
}

@Preview
@Composable
@ExperimentalPagerApi
fun CamerasScreenPreview() {
    val camera = Camera(
        1,
        "Camera",
        "Image",
        "Room",
        true,
        rec = true
    )
    CamerasScreen(
        listOf(camera,camera)
    )
}


package com.example.compose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.compose.services.database.CameraRealm
import com.example.compose.ui.theme.AppBackground
import io.realm.Realm

@Composable
fun CamerasScreen() {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        itemsIndexed(Realm.getDefaultInstance().where(CameraRealm::class.java).findAll()) { _, camera ->
            CameraItem(camera)
        }
    }
}
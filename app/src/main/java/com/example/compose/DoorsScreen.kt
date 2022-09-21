package com.example.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose.models.Door
import com.example.compose.ui.theme.AppBackground

@Composable
fun DoorsScreen(doors: List<Door>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().background(AppBackground)
    ) {
        itemsIndexed(doors) { _, door ->
            DoorItem(door)
        }
    }
}

@Preview
@Composable
fun DoorScreenPreview(){
    val doors = listOf(
        Door("Кабинет"),
        Door("Зал"),
        Door("Веранда"),
        Door("Домофон", R.drawable.test_img)
    )
    DoorsScreen(doors)
}
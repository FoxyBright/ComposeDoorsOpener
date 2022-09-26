package com.example.compose.composable

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
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun DoorsScreen(doors: List<Door>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        itemsIndexed(doors) { _, door ->
            DoorItem(door)
        }
    }
}

@Preview
@Composable
@ExperimentalPagerApi
fun DoorsScreenPreview() {
    val doorWithImage = Door(
        1,
        "Door",
        "Image",
        "Room",
        true
    )
    val door = Door(
        1,
        "Door",
        null,
        "Room",
        true
    )
    DoorsScreen(
        listOf(door, door, door, doorWithImage)
    )
}

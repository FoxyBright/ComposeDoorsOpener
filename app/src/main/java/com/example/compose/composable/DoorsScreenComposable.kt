package com.example.compose.composable

import android.app.AlertDialog
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.models.Door
import com.example.compose.services.database.setDoorFavorite
import com.example.compose.services.database.setDoorName
import com.example.compose.ui.theme.*
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun DoorsScreen(doors: List<Door>, doorsViewModel: DoorsViewModel) {
    val context = LocalContext.current
    val revealedDoorsIdsList by doorsViewModel.revealedDoorsIdsList.collectAsState()
    val dialogState = remember { mutableStateOf(false) }
    val doorName = remember { mutableStateOf("") }
    val doorId = remember { mutableStateOf(0) }
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        itemsIndexed(doors) { _, door ->
            var imageResource by remember {
                mutableStateOf(
                    if (door.favorites)
                        R.drawable.favorite_activate
                    else
                        R.drawable.favorite_disactivate
                )
            }
            Box(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    if (dialogState.value) {
                        AlertDialog(
                            { dialogState.value = false },
                            {
                                Row(
                                    Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth(),
                                    Arrangement.End
                                ) {
                                    Text(
                                        "Отмена",
                                        Modifier
                                            .padding(5.dp)
                                            .clickable {
                                                dialogState.value = false
                                                doorsViewModel.onItemCollapsed(doorId.value)
                                            },
                                        GrandBlue,
                                        18.sp,
                                        fontFamily = Circle
                                    )
                                    Text(
                                        "Сохранить",
                                        Modifier
                                            .padding(5.dp)
                                            .clickable {
                                                dialogState.value = false
                                                setDoorName(doorId.value, doorName.value)
                                                doorsViewModel.onItemCollapsed(doorId.value)
                                            },
                                        GrandBlue,
                                        18.sp,
                                        fontFamily = Circle
                                    )
                                }
                            },
                            title = {
                                Text(
                                    "Введите новое имя:",
                                    Modifier.padding(5.dp),
                                    TextColor,
                                    18.sp,
                                    fontFamily = Circle
                                )
                            },
                            text = {
                                TextField(doorName.value, { doorName.value = it })
                            }
                        )
                    }
                    Image(
                        painterResource(R.drawable.edit),
                        stringResource(R.string.edit),
                        Modifier
                            .padding(start = 5.dp, top = 10.dp)
                            .size(46.dp)
                            .clickable {
                                doorName.value = door.name
                                doorId.value = door.id
                                dialogState.value = true
                            })
                    Image(
                        painterResource(imageResource),
                        stringResource(R.string.favorite),
                        Modifier
                            .padding(start = 5.dp, top = 10.dp)
                            .size(46.dp)
                            .clickable {
                                setDoorFavorite(door.id, !door.favorites)
                                if (door.favorites)
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(
                                                R.string.delete_from_favorite,
                                                door.name
                                            ),
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                else
                                    Toast
                                        .makeText(
                                            context,
                                            context.getString(R.string.add_to_favorite, door.name),
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                imageResource = if (door.favorites)
                                    R.drawable.favorite_disactivate
                                else
                                    R.drawable.favorite_activate
                                doorsViewModel.onItemCollapsed(door.id)
                            })
                }
                DoorItem(
                    door, revealedDoorsIdsList.contains(door.id),
                    { doorsViewModel.onItemCollapsed(door.id) },
                    { doorsViewModel.onItemExpanded(door.id) })
            }
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
        listOf(door, door, door, doorWithImage),
        DoorsViewModel()
    )
}
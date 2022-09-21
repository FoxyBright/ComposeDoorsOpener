package com.example.compose

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.models.Door
import com.example.compose.ui.theme.NetworkColor
import com.example.compose.ui.theme.TextColor
import com.example.compose.ui.theme.White

@Composable
fun DoorItem(door: Door) {
    val context = LocalContext.current
    val doorName by remember {
        mutableStateOf(door.name)
    }
    val rounded: Int
    val statusBarPadding: Int
    val textPadding: Int
    if (door.image != 0) {
        rounded = 0
        statusBarPadding = 0
        textPadding = 0
    } else {
        rounded = 10
        statusBarPadding = 10
        textPadding = 5
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            if (door.image != 0) {
                Card {
                    Image(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(
                                shape = RoundedCornerShape(
                                    topStart = 10.dp,
                                    topEnd = 10.dp
                                )
                            ),
                        painter = painterResource(door.image),
                        contentScale = ContentScale.Crop,
                        contentDescription = "camera's preview"
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Image(
                            modifier = Modifier
                                .size(60.dp)
                                .padding(top = 5.dp)
                                .clickable(
                                    onClick = {
                                        Toast
                                            .makeText(
                                                context,
                                                "$doorName не отвечает",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                ),
                            contentScale = ContentScale.Crop,
                            painter = painterResource(
                                id = R.drawable.play_button
                            ),
                            contentDescription = "play"
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp,
                            topStart = rounded.dp,
                            topEnd = rounded.dp
                        )
                    )
                    .background(White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = statusBarPadding.dp)
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = textPadding.dp),
                            text = doorName,
                            fontSize = 17.sp,
                            color = TextColor
                        )
                        if (door.image != 0) {
                            Text(
                                text = "В сети",
                                fontSize = 14.sp,
                                color = NetworkColor
                            )
                        }
                    }
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .padding(top = 5.dp)
                            .clickable(
                                onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Дверь $doorName открыта",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            ),
                        painter = painterResource(
                            id = R.drawable.locker
                        ),
                        contentDescription = "locker"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DoorItemWithImagePreview() {
    DoorItem(Door("Домофон", R.drawable.test_img))
}

@Preview
@Composable
fun DoorItemWithoutImagePreview() {
    DoorItem(Door("Веранда"))
}
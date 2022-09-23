package com.example.compose.composable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose.R
import com.example.compose.models.Door
import com.example.compose.ui.theme.NetworkColor
import com.example.compose.ui.theme.TextColor
import com.example.compose.ui.theme.White

@Composable
fun DoorItem(door: Door) {
    val context = LocalContext.current
    val rounded: Int
    val statusBarPadding: Int
    val textPadding: Int
    if (door.snapshot != null) {
        rounded = 0
        statusBarPadding = 0
        textPadding = 0
    } else {
        rounded = 10
        statusBarPadding = 10
        textPadding = 5
    }
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Column {
            if (door.snapshot != null) {
                Card {
                    AsyncImage(
                        door.snapshot,
                        "camera's preview",
                        Modifier
                            .fillMaxWidth()
                            .height(230.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        Modifier
                            .fillMaxWidth()
                            .height(230.dp),
                        Arrangement.SpaceAround,
                        Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.play_button),
                            "play",
                            Modifier
                                .size(60.dp)
                                .padding(top = 5.dp)
                                .clickable(
                                    onClick = {
                                        Toast
                                            .makeText(
                                                context,
                                                "${door.name} не отвечает",
                                                Toast.LENGTH_SHORT
                                            )
                                            .show()
                                    }
                                ),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 10.dp,
                            bottomEnd = 10.dp,
                            topStart = rounded.dp,
                            topEnd = rounded.dp
                        )
                    )
                    .background(White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    Arrangement.SpaceBetween
                ) {
                    Column(
                        Modifier
                            .padding(bottom = statusBarPadding.dp)
                    ) {
                        Text(
                            door.name,
                            Modifier
                                .padding(textPadding.dp),
                            TextColor,
                            17.sp
                        )
                        if (door.snapshot != null) {
                            Text(
                                "В сети",
                                color = NetworkColor,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    Image(
                        painterResource(R.drawable.locker),
                        "locker",
                        Modifier
                            .size(32.dp)
                            .padding(top = 5.dp)
                            .clickable(
                                onClick = {
                                    Toast
                                        .makeText(
                                            context,
                                            "Дверь ${door.name} открыта",
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                }
                            )
                    )
                }
            }
        }
    }
}
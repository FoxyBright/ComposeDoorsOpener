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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose.R
import com.example.compose.models.Camera
import com.example.compose.ui.theme.Circle
import com.example.compose.ui.theme.HeadersColor
import com.example.compose.ui.theme.TextColor
import com.example.compose.ui.theme.White
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
fun CameraItem(camera: Camera) {
    val context = LocalContext.current
    Box(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column {
            Text(
                camera.room,
                Modifier
                    .padding(start = 2.dp, bottom = 16.dp),
                HeadersColor,
                21.sp,
                fontFamily = Circle
            )
            Card {
                AsyncImage(
                    camera.snapshot,
                    stringResource(R.string.camera_preview),
                    Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                    painterResource(R.drawable.test_img),
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
                        stringResource(R.string.play),
                        Modifier
                            .size(60.dp)
                            .padding(top = 5.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(
                                            R.string.object_not_responding,
                                            camera.name
                                        ),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            },
                        contentScale = ContentScale.Crop
                    )
                }
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(7.dp)
                ) {
                    if (camera.rec) {
                        Image(
                            painterResource(R.drawable.rec),
                            stringResource(R.string.record),
                            Modifier
                                .size(20.dp)
                                .align(Alignment.CenterStart)
                        )
                    }
                    if (camera.favorites) {
                        Image(
                            painterResource(R.drawable.star),
                            stringResource(R.string.favorite),
                            Modifier
                                .size(20.dp)
                                .align(Alignment.CenterEnd)
                        )
                    }
                }
            }
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
                    .background(White)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 16.dp, bottom = 26.dp),
                    Arrangement.SpaceBetween
                ) {
                    Text(
                        camera.name,
                        fontSize = 17.sp,
                        color = TextColor,
                        fontFamily = Circle
                    )
                }
            }
        }
    }
}

@Preview
@Composable
@ExperimentalPagerApi
fun CameraItemPreview() {
    CameraItem(
        Camera(
            1,
            "Camera",
            "Image",
            "Room",
            true,
            rec = true
        )
    )
}

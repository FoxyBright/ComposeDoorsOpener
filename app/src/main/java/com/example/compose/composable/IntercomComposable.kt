package com.example.compose.composable

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.ui.theme.*

@Composable
fun IntercomComposable(navController: NavController) {
    val context = LocalContext.current
    Box(
        Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.communal_intercom),
                Modifier.padding(top = 60.dp, bottom = 18.dp),
                HeadersColor,
                21.sp
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            ) {
                Image(
                    painterResource(R.drawable.text_intercom),
                    stringResource(R.string.communal_intercom),
                    Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
                Image(
                    painterResource(R.drawable.top_transparency_block),
                    stringResource(R.string.transparency),
                    Modifier
                        .fillMaxWidth()
                        .height(70.dp),
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    Modifier
                        .padding(8.dp)
                        .align(Alignment.TopEnd)
                ) {
                    Image(
                        painterResource(R.drawable.eye),
                        stringResource(R.string.eye),
                        Modifier.width(24.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    Text(
                        stringResource(R.string.visitors),
                        Modifier.padding(horizontal = 8.dp),
                        White, 16.sp
                    )
                }
                Image(
                    painterResource(R.drawable.bottom_transparency_block),
                    stringResource(R.string.transparency),
                    Modifier
                        .fillMaxWidth()
                        .height(70.dp)
                        .align(Alignment.BottomCenter),
                    contentScale = ContentScale.FillWidth
                )
                Row(
                    Modifier
                        .padding(vertical = 16.dp)
                        .align(Alignment.BottomStart),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painterResource(R.drawable.red_circle),
                        stringResource(R.string.live_broadcast),
                        Modifier
                            .padding(start = 16.dp)
                            .width(14.dp),
                        contentScale = ContentScale.FillWidth,
                    )
                    Text(
                        stringResource(R.string.live_broadcast),
                        Modifier.padding(start = 8.dp),
                        White, 12.sp
                    )
                }
                Image(
                    painterResource(R.drawable.change_video_size),
                    stringResource(R.string.change_vide_size),
                    Modifier
                        .padding(16.dp)
                        .width(16.dp)
                        .align(Alignment.BottomEnd),
                    contentScale = ContentScale.FillWidth
                )
            }
        }
        Box(Modifier.align(Alignment.BottomCenter)) {
            Column {

                Card(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(bottom = 16.dp)
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    context.getString(R.string.door_is_opened, ""),
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        },
                    elevation = 1.dp
                ) {
                    Column(
                        Modifier.padding(top = 20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painterResource(R.drawable.key),
                            stringResource(R.string.open_door),
                            Modifier.size(40.dp)
                        )
                        Text(
                            stringResource(R.string.open_door),
                            Modifier.padding(vertical = 12.dp),
                            TextColor, 14.sp
                        )
                    }
                }
                Column {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .background(Shadow)
                            .height(1.dp)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .background(White)
                            .padding(start = 8.dp, end = 16.dp, top = 14.dp, bottom = 30.dp),
                        Arrangement.SpaceBetween
                    ) {
                        Image(
                            painterResource(R.drawable.back),
                            stringResource(R.string.back),
                            Modifier
                                .size(32.dp)
                                .clickable {
                                    navController.navigate("home")
                                })
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Image(
                                painterResource(R.drawable.top_arrow),
                                stringResource(R.string.open_settings),
                                Modifier.width(23.dp)
                            )
                            Text(
                                stringResource(R.string.settings),
                                Modifier,
                                SettingsColor,
                                17.sp
                            )
                        }
                        Image(
                            painterResource(R.drawable.black_screen),
                            stringResource(R.string.black_screen),
                            Modifier.size(32.dp),
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun IntercomComposablePreview() {
    IntercomComposable(rememberNavController())
}
package com.example.compose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.models.Camera
import com.example.compose.models.Door
import com.example.compose.ui.theme.AppBackground
import com.example.compose.ui.theme.Circle
import com.example.compose.ui.theme.HeadersColor
import com.example.compose.viewModel.CamerasViewModel
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
@ExperimentalPagerApi
fun MainScreen(
    doors: List<Door>,
    cameras: List<Camera>,
    camerasViewModel: CamerasViewModel,
    doorsViewModel: DoorsViewModel,
    navController: NavController
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(AppBackground)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.1f)
                .padding(bottom = 10.dp),
            Arrangement.Bottom,
            Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.my_home),
                fontSize = 21.sp,
                color = HeadersColor,
                fontFamily = Circle
            )
        }
        TabScreen(doors, cameras, camerasViewModel, doorsViewModel, navController)
    }
}

@Preview
@Composable
@ExperimentalPagerApi
fun MainScreenPreview() {
    val camera = Camera(
        1,
        "Camera",
        "Image",
        "Room",
        true,
        rec = true
    )
    val door = Door(
        1,
        "Door",
        "Image",
        "Room",
        true
    )
    MainScreen(
        listOf(door, door),
        listOf(camera, camera),
        CamerasViewModel(),
        DoorsViewModel(),
        rememberNavController()
    )
}
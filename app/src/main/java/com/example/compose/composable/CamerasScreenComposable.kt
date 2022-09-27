package com.example.compose.composable

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.R
import com.example.compose.models.Camera
import com.example.compose.ui.theme.AppBackground
import com.example.compose.viewModel.CamerasViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
@SuppressLint("StateFlowValueCalledInComposition")
fun CamerasScreen(cameras: List<Camera>, camerasViewModel: CamerasViewModel) {
    val context = LocalContext.current
    val revealedCamerasIdsList by camerasViewModel.revealedCamerasIdsList.collectAsState()

    LazyColumn(
        Modifier
            .fillMaxSize()
            .background(AppBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        itemsIndexed(cameras) { _, camera ->
            Box(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Image(
                        painterResource(R.drawable.favorite_disactivate),
                        stringResource(R.string.favorite),
                        Modifier
                            .padding(start = 5.dp, top = 10.dp)
                            .size(46.dp)
                            .clickable {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.favorite),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            })
                }
                CameraItem(
                    camera, revealedCamerasIdsList.contains(camera.id),
                    { camerasViewModel.onItemCollapsed(camera.id) },
                    { camerasViewModel.onItemExpanded(camera.id) })
            }
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
        listOf(camera),
        CamerasViewModel()
    )
}


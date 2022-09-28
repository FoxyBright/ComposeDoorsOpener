package com.example.compose.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.R
import com.example.compose.models.Camera
import com.example.compose.models.Door
import com.example.compose.ui.theme.*
import com.example.compose.viewModel.CamerasViewModel
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabScreen(
    doors: List<Door>,
    cameras: List<Camera>,
    camerasViewModel: CamerasViewModel,
    doorsViewModel: DoorsViewModel
) {
    val pagerState = rememberPagerState(0)
    Column {
        val list: List<String> =
            listOf(stringResource(R.string.cameras), stringResource(R.string.doors))
        val scope = rememberCoroutineScope()
        TabRow(
            pagerState.currentPage,
            backgroundColor = AppBackground,
            contentColor = White,
            divider = { TabRowDefaults.Divider(thickness = 2.dp, color = Shadow) },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions), 2.dp, GrandBlue
                )
            }
        ) {
            list.forEachIndexed { index, title ->
                Tab(
                    pagerState.currentPage == index,
                    {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            title,
                            color = HeadersColor,
                            fontFamily = Circle,
                            fontSize = 17.sp
                        )
                    }
                )
            }
        }
        HorizontalPager(list.size, state = pagerState) { page ->
            when (page) {
                0 -> CamerasScreen(cameras, camerasViewModel)
                1 -> DoorsScreen(doors, doorsViewModel)
            }
        }
    }
}

@Preview
@Composable
@ExperimentalPagerApi
fun TabScreenPreview() {
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
    TabScreen(
        listOf(door, door),
        listOf(camera, camera),
        CamerasViewModel(),
        DoorsViewModel()
    )
}
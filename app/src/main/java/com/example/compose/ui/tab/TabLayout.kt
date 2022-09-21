package com.example.compose.ui.tab

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.CamerasScreen
import com.example.compose.DoorsScreen
import com.example.compose.models.Camera
import com.example.compose.models.Door
import com.example.compose.ui.theme.*
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabScreen(doors: List<Door>, cameras: List<Camera>) {
    val pagerState = rememberPagerState(2)
    Column{
        val list = listOf("Камеры", "Двери")
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = AppBackground,
            contentColor = White,
            divider = {
                TabRowDefaults.Divider(
                    thickness = 2.dp,
                    color = Shadow
                )
            },
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 2.dp,
                    color = GrandBlue
                )
            }
        ) {
            list.forEachIndexed { index, _->
                Tab(
                    text = {
                        Text(
                            list[index],
                            color = HeadersColor,
                            fontFamily = Circle,
                            fontSize = 17.sp
                        )
                    },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                )
            }
        }
        HorizontalPager(state = pagerState, count = 2) { page ->
            when(page) {
                0 -> CamerasScreen(cameras)
                1 -> DoorsScreen(doors)
            }
        }
    }
}
package com.example.compose.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.models.Door
import com.example.compose.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@Composable
fun TabScreen(doors: List<Door>) {
    val pagerState = rememberPagerState(0)
    Column {
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
            list.forEachIndexed { index, title ->
                Tab(
                    text = {
                        Text(
                            text = title,
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
        HorizontalPager(state = pagerState, count = list.size) { page ->
            when (page) {
                0 -> CamerasScreen()
                1 -> DoorsScreen(doors)
            }
        }
    }
}

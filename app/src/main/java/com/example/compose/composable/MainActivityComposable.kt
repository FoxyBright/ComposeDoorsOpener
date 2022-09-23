package com.example.compose.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.models.Door
import com.example.compose.ui.theme.AppBackground
import com.example.compose.ui.theme.Circle
import com.example.compose.ui.theme.HeadersColor
import com.google.accompanist.pager.ExperimentalPagerApi

@Composable
@ExperimentalPagerApi
fun MainScreen(doors: List<Door>) {
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
                "Мой дом",
                fontSize = 21.sp,
                color = HeadersColor,
                fontFamily = Circle
            )
        }
        TabScreen(doors)
    }
}
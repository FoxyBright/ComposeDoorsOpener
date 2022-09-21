package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.models.Camera
import com.example.compose.models.Door
import com.example.compose.ui.tab.TabScreen
import com.example.compose.ui.theme.AppBackground
import com.example.compose.ui.theme.Circle
import com.example.compose.ui.theme.HeadersColor
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreenPreview()
        }
    }

    @Preview
    @Composable
    @ExperimentalPagerApi
    fun MainScreenPreview() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AppBackground),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.1f)
                    .padding(bottom = 10.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Мой дом",
                    fontSize = 21.sp,
                    color = HeadersColor,
                    fontFamily = Circle
                )
            }
            TabScreen(Door().getDoors(), Camera().getCameras())
        }
    }
}
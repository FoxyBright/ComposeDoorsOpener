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
import com.example.compose.services.retrofit.uploadData
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

            val dr = listOf(
                Door("Кабинет", null, "", true, 1),
                Door("Зал", null, "", true, 1),
                Door("Веранда", null, "", true, 1),
                Door("Домофон", "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png", "", true, 1)
            )

            val cam = listOf(
                Camera("Камера 1", "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png", "Кабинет", true, rec = false, 1),
                Camera("Камера 2", "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png", "Зал", true, rec = false, 2),
                Camera("Камера 3", "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png", "Веранда", false, rec = true, 3),
                Camera("Камера 4", "https://serverspace.ru/wp-content/uploads/2019/06/backup-i-snapshot.png", "Домофон", true, rec = false,4)
            )
            uploadData()
            TabScreen(dr, cam)
        }
    }
}
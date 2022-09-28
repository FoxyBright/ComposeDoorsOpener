package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import com.example.compose.composable.MainScreen
import com.example.compose.services.database.uploadData
import com.example.compose.viewModel.CamerasViewModel
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.realm.Realm

class MainActivity : ComponentActivity() {
    private val camerasViewModel by viewModels<CamerasViewModel>()
    private val doorsViewModel by viewModels<DoorsViewModel>()
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Realm.init(this)
            uploadData()
            MainScreen(
                DoorsViewModel().doors.collectAsState(listOf()).value,
                CamerasViewModel().cameras.collectAsState(listOf()).value,
                camerasViewModel,
                doorsViewModel
            )
        }
    }
}

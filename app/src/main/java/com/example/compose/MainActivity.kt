package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.composable.IntercomComposable
import com.example.compose.composable.MainScreen
import com.example.compose.services.database.uploadData
import com.example.compose.viewModel.CamerasViewModel
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.realm.Realm

class MainActivity : ComponentActivity() {
    private val doorsViewModel by viewModels<DoorsViewModel>()
    private val camerasViewModel by viewModels<CamerasViewModel>()

    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Realm.init(this)
        uploadData()
        setContent {
            val navController = rememberNavController()
            NavHost(navController, "home") {
                composable("home") {
                    MainScreen(
                        doorsViewModel.doors.collectAsState(listOf()).value,
                        camerasViewModel.cameras.collectAsState(listOf()).value,
                        camerasViewModel,
                        doorsViewModel,
                        navController
                    )
                }
                composable("intercom") { IntercomComposable(navController) }
            }
        }
    }
}

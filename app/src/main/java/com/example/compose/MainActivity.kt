package com.example.compose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.compose.composable.MainScreen
import com.example.compose.services.database.uploadData
import com.example.compose.viewModel.CamerasViewModel
import com.example.compose.viewModel.DoorsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.realm.Realm

class MainActivity : ComponentActivity() {
    @ExperimentalPagerApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Realm.init(this)
            uploadData()
            MainScreen(
                DoorsViewModel().doors.collectAsState(listOf()).value,
                CamerasViewModel().cameras.collectAsState(listOf()).value
            )
        }
    }
}
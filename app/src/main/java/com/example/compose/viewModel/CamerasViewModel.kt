package com.example.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.models.Camera
import com.example.compose.services.database.CameraRealm
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class CamerasViewModel : ViewModel() {
    val cameras: Flow<List<Camera>> = Realm.getDefaultInstance()
        .where(CameraRealm::class.java)
        .findAll()
        .toFlow()
        .map { it.map(CameraRealm::map) }
        .flowOn(Dispatchers.Main)

    init {
        cameras.launchIn(viewModelScope)
    }
}
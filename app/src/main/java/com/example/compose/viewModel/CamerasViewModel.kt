package com.example.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.models.Camera
import com.example.compose.services.database.CameraRealm
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

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

    private val _revealedCamerasIdsList = MutableStateFlow(listOf<Int>())
    val revealedCamerasIdsList: StateFlow<List<Int>> get() = _revealedCamerasIdsList

    fun onItemExpanded(cameraId: Int) {
        if (_revealedCamerasIdsList.value.contains(cameraId)) return
        _revealedCamerasIdsList.value = _revealedCamerasIdsList.value.toMutableList().also { list ->
            if (list.size > 0)
                list.clear()
            list.add(cameraId)
        }
    }

    fun onItemCollapsed(cameraId: Int) {
        if (!_revealedCamerasIdsList.value.contains(cameraId)) return
        _revealedCamerasIdsList.value = _revealedCamerasIdsList.value.toMutableList().also { list ->
            list.remove(cameraId)
        }
    }
}
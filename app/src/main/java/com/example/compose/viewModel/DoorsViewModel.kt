package com.example.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.models.Door
import com.example.compose.services.database.DoorRealm
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class DoorsViewModel : ViewModel() {
    val doors: Flow<List<Door>> = Realm.getDefaultInstance()
        .where(DoorRealm::class.java)
        .findAll()
        .toFlow()
        .map { it.map(DoorRealm::map) }
        .flowOn(Dispatchers.Main)

    init {
        doors.launchIn(viewModelScope)
    }
}
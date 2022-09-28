package com.example.compose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose.models.Door
import com.example.compose.services.database.DoorRealm
import io.realm.Realm
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

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

    private val _revealedDoorsIdsList = MutableStateFlow(listOf<Int>())
    val revealedDoorsIdsList: StateFlow<List<Int>> get() = _revealedDoorsIdsList

    fun onItemExpanded(doorId: Int) {
        if (_revealedDoorsIdsList.value.contains(doorId)) return
        _revealedDoorsIdsList.value = _revealedDoorsIdsList.value.toMutableList().also { list ->
            if (list.size > 0)
                list.clear()
            list.add(doorId)
        }
    }

    fun onItemCollapsed(doorId: Int) {
        if (!_revealedDoorsIdsList.value.contains(doorId)) return
        _revealedDoorsIdsList.value = _revealedDoorsIdsList.value.toMutableList().also { list ->
            list.remove(doorId)
        }
    }

    fun allCollapse() {
        _revealedDoorsIdsList.value = _revealedDoorsIdsList.value.toMutableList().also { list ->
            list.clear()
        }
    }

}
package com.example.compose.services.database

import com.example.compose.models.Door
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class DoorRealm : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var snapshot: String? = null
    var room: String? = null
    var favorites: Boolean = false

    fun map() = Door(id, name, snapshot, room, favorites)
}
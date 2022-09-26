package com.example.compose.services.database

import com.example.compose.models.Camera
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

@RealmClass
open class CameraRealm : RealmObject() {
    @PrimaryKey
    var id: Int = 0
    var name: String = ""
    var snapshot: String = ""
    var room: String = ""
    var favorites: Boolean = false
    var rec: Boolean = false

    fun map() = Camera(id, name, snapshot, room, favorites, rec)
}

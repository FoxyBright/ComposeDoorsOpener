package com.example.compose.services.database

import com.example.compose.models.Door
import io.realm.Realm
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

fun setDoorFavorite(id: Int, favorites: Boolean) {
    val realm = Realm.getDefaultInstance()
    realm.executeTransactionAsync {
        it.where(DoorRealm::class.java)
            .equalTo("id", id)
            .findFirst()
            ?.favorites = favorites
    }
    realm.close()
}

fun setDoorName(id: Int, name: String) {
    val realm = Realm.getDefaultInstance()
    realm.executeTransactionAsync {
        it.where(DoorRealm::class.java)
            .equalTo("id", id)
            .findFirst()
            ?.name = name
    }
    realm.close()
}
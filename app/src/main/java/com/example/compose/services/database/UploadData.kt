package com.example.compose.services.database

import com.example.compose.services.retrofit.API
import com.example.compose.services.retrofit.RetrofitAPI
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun uploadData() {
    uploadDoors()
    uploadCameras()
}

fun uploadCameras() {
    CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitAPI.instance.create(API::class.java).getCameras()
        if (response.isSuccessful) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            for (camera in response.body()?.cameras!!) {
                val c = realm.where(CameraRealm::class.java)
                    .equalTo("id", camera.id).findFirst()
                if (c == null) {
                    val camRealm = realm.createObject(CameraRealm::class.java, camera.id)
                    camRealm.name = camera.name
                    camRealm.room = camera.room
                    camRealm.favorites = camera.favorites
                    camRealm.snapshot = camera.snapshot
                    camRealm.rec = camera.rec
                } else {
                    c.name = camera.name
                    c.room = camera.room
                    c.favorites = camera.favorites
                    c.snapshot = camera.snapshot
                    c.rec = camera.rec
                }
            }
            realm.commitTransaction()
            realm.close()
        }
    }
}

fun uploadDoors() {
    CoroutineScope(Dispatchers.IO).launch {
        val response = RetrofitAPI.instance.create(API::class.java).getDoors()
        if (response.isSuccessful) {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            for (door in response.body()?.doors!!) {
                val d = realm.where(DoorRealm::class.java)
                    .equalTo("id", door.id).findFirst()
                if (d == null) {
                    val doorRealm = realm.createObject(DoorRealm::class.java, door.id)
                    doorRealm.name = door.name
                    doorRealm.room = door.room
                    doorRealm.favorites = door.favorites
                    doorRealm.snapshot = door.snapshot
                } else {
                    d.name = door.name
                    d.room = door.room
                    d.favorites = door.favorites
                    d.snapshot = door.snapshot
                }
            }
            realm.commitTransaction()
            realm.close()
        }
    }
}
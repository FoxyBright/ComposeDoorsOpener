package com.example.compose.services.database

import android.util.Log
import com.example.compose.services.retrofit.responces.CamerasResponse
import com.example.compose.services.retrofit.responces.DoorResponse
import com.example.compose.services.retrofit.API
import com.example.compose.services.retrofit.RetrofitAPI
import io.realm.Realm
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun uploadData() {
    uploadDoors()
    uploadCameras()
}

fun uploadCameras() {
    RetrofitAPI.instance.create(API::class.java).getCameras()
        .enqueue(object : Callback<CamerasResponse> {
            override fun onResponse(
                call: Call<CamerasResponse>,
                response: Response<CamerasResponse>
            ) {
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

            override fun onFailure(call: Call<CamerasResponse>, t: Throwable) {
                Log.d("Error", "Upload cameras")
            }
        })
}

fun uploadDoors() {
    RetrofitAPI.instance.create(API::class.java).getDoors().enqueue(
        object : Callback<DoorResponse> {
            override fun onResponse(
                call: Call<DoorResponse>,
                response: Response<DoorResponse>
            ) {
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

            override fun onFailure(call: Call<DoorResponse>, t: Throwable) {
                Log.d("Error", "Upload doors")
            }
        })
}
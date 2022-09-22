package com.example.compose.services.retrofit

import com.example.compose.services.responces.CamerasResponse
import com.example.compose.services.responces.DoorResponse
import retrofit2.Call
import retrofit2.http.GET

interface API {

    @GET("api/rubetek/cameras/")
    fun getCameras(): Call<CamerasResponse>

    @GET("api/rubetek/doors/")
    fun getDoors(): Call<DoorResponse>
}
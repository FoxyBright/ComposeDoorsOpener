package com.example.compose.services.retrofit

import com.example.compose.services.retrofit.responces.CamerasResponse
import com.example.compose.services.retrofit.responces.DoorResponse
import retrofit2.Response
import retrofit2.http.GET

interface API {

    @GET("api/rubetek/cameras/")
    suspend fun getCameras(): Response<CamerasResponse>

    @GET("api/rubetek/doors/")
    suspend fun getDoors(): Response<DoorResponse>
}
package com.example.compose.services.retrofit

import android.util.Log
import com.example.compose.services.responces.CamerasResponse
import com.example.compose.services.responces.DoorResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun uploadData() {
    RetrofitAPI.instance.create(API::class.java).getCameras()
        .enqueue(object : Callback<CamerasResponse> {
            override fun onResponse(
                call: Call<CamerasResponse>,
                response: Response<CamerasResponse>
            ) {
                response.body()?.cameras?.forEach {
                    Log.d("CallLog", it.name)
                }
            }

            override fun onFailure(call: Call<CamerasResponse>, t: Throwable) {
                Log.d("Error", "Upload cameras")
            }
        })

    RetrofitAPI.instance.create(API::class.java).getDoors()
        .enqueue(object : Callback<DoorResponse> {
            override fun onResponse(
                call: Call<DoorResponse>,
                response: Response<DoorResponse>
            ) {
                response.body()?.doors?.forEach {
                    Log.d("CallLog", it.name)
                }
            }

            override fun onFailure(call: Call<DoorResponse>, t: Throwable) {
                Log.d("Error", "Upload doors")
            }
        })
}
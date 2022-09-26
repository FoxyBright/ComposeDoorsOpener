package com.example.compose.services.retrofit.responces

import com.example.compose.models.Data

class CamerasResponse {
    private val data: Data? = null
    val cameras get() = data?.cameras
}
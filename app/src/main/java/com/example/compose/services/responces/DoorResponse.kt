package com.example.compose.services.responces

import com.example.compose.models.Door
import com.google.gson.annotations.SerializedName

class DoorResponse {
    @SerializedName("data")
    val doors: List<Door>? = null
}
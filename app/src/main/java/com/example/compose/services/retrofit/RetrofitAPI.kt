
package com.example.compose.services.retrofit

import com.example.compose.BuildConfig
import com.localebro.okhttpprofiler.OkHttpProfilerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitAPI {
    val instance: Retrofit by lazy {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG)
            builder.addInterceptor(OkHttpProfilerInterceptor())
        val client = builder.build()
        Retrofit.Builder()
            .baseUrl("http://cars.cprogroup.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}
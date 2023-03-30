package com.uktamov.mvvm.networking

import com.uktamov.mvvm.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("photos")
    suspend fun getPhotos(): Response<ImageResponse>
}
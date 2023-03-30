package com.uktamov.mvvm.repository

import com.uktamov.mvvm.networking.ApiService

class RemoteRepository(
    private val apiService: ApiService
) {
    suspend fun getPhotos() = apiService.getPhotos()
}
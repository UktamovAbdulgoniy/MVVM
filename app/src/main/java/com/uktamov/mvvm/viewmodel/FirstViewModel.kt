package com.uktamov.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uktamov.mvvm.model.ImageResponseItem
import com.uktamov.mvvm.networking.RetrofitClient
import com.uktamov.mvvm.repository.RemoteRepository
import kotlinx.coroutines.launch

class FirstViewModel:ViewModel() {
    private val apiService by lazy { RetrofitClient.providerRetrofit() }
    private val repository = RemoteRepository(apiService)

    private val _images = MutableLiveData<ArrayList<ImageResponseItem>>()
    val images:LiveData<ArrayList<ImageResponseItem>> get() = _images

    fun callApi(){
        viewModelScope.launch {
            try {
                val response = repository.getPhotos()
                if (response.isSuccessful){
                    _images.postValue(response.body())
                }
            }catch (e:Exception){
                e.printStackTrace()
            }
        }
    }
}
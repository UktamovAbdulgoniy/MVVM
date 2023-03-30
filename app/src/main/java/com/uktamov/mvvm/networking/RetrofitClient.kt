package com.uktamov.mvvm.networking

import com.uktamov.mvvm.util.Constants
import com.uktamov.mvvm.util.Constants.BASE_URL
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    fun providerRetrofit(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okClient())
            .build()
            .create(ApiService::class.java)
    }

    private fun okClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(Interceptor { chain ->
                val builder = chain.request().newBuilder()
                builder.header(
                    "Authorization",
                    "Client-ID EXuIdDibpwC9_vce0k3VNRkmXQ1WtY4EzlcH5cD91cM"
                )
                chain.proceed(builder.build())
            })
            .build()
    }
}
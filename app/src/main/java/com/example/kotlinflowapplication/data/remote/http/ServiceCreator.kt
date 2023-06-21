package com.example.kotlinflowapplication.data.remote.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ServiceCreator {
    private val BASE_URL = "https://www.wanandroid.com/"

    var retrofit: Retrofit

    init {
        val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(AddCookiesInterceptor())
        val builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
        retrofit = builder.build()
    }

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    companion object {
        private var serviceCreator: ServiceCreator? = null
        fun getInstance(): ServiceCreator {
            if (serviceCreator == null) {
                synchronized(ServiceCreator::class.java) {
                    if (serviceCreator == null) {
                        serviceCreator = ServiceCreator()
                    }

                }
            }
            return serviceCreator!!
        }
    }
}
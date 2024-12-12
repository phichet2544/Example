package com.example.jpapi.API.lazy

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object lazyBuild {
    val baseURL = "https://jsonplaceholder.typicode.com/"
    val api: ApiserviceLazy by lazy{
        Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiserviceLazy::class.java)
    }
}
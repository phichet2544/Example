package com.example.jpapi.API.lazy

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiserviceLazy {
    @GET("todos")
    suspend fun getPosts(): List <MDdata>

    @GET("todos")
    suspend fun getFilteredItems(@Query("completed") completed: Boolean): List <MDdata>


}
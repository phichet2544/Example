package com.example.jpapi.API.lazy

import com.google.gson.annotations.SerializedName

data class MDdata(
    @SerializedName("userId")
    val userId:Int,
    @SerializedName("id")
    val id:Int,
    @SerializedName("title")
    val title:String,
    @SerializedName("completed")
    val completed:Boolean){
}

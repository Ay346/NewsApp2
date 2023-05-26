package com.example.newsapp.data.base

import com.google.gson.annotations.SerializedName
//open
data class BaseResponse<T>(
    val status: String,
    @SerializedName(value = "articles")
    val dataNews: T,
    @SerializedName(value = "sources")
    val dataSource:T,
    val code: String
)

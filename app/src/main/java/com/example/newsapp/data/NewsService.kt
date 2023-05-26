package com.akshay.newsapp.news.api

import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.NewsResponse
import com.example.newsapp.data.base.BaseResponse
import com.example.newsapp.data.model.Source
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsService {


    @GET("top-headlines?apiKey=f35e481503864d5a966f21a394211844")
    suspend fun getNews(@Query("country") country: String,@Query("category") category: String): Response<BaseResponse<List<NewsArticle>>>

    @GET("top-headlines/sources?apiKey=f35e481503864d5a966f21a394211844")
    suspend fun getSource():Response<BaseResponse<List<Source>>>
}

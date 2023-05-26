package com.example.newsapp.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Describes the response from news service API.
 */
data class NewsResponse(
    @SerializedName("articles")
    val articles: List<NewsArticle> ,
    val status: String
)

@Parcelize
data class NewsArticle(

    @SerializedName("author")
    val author: String?=null,


    @SerializedName("title")
    val title: String,


//    @SerializedName("url")
//    val url: String?=null,


    @SerializedName("urlToImage")
    val urlToImage: String? = null,

    @SerializedName("publishedAt")
    val publishedAt: String,

    @SerializedName("content")
    val content: String,

) : Parcelable {

}
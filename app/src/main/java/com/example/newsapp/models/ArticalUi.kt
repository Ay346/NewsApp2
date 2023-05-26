package com.example.newsapp.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
 data class ArticalUi(
    val author: String,
    val title: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String,
    var isBookMarked:Boolean
): Parcelable
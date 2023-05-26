package com.example.newsapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ArticalEntity(
    val author: String,
    val title: String,
    @PrimaryKey
    val url: String,
    val publishedAt: String,
    val content: String,
)

package com.example.newsapp.mapper

import com.example.newsapp.data.NewsArticle
import com.example.newsapp.db.ArticalEntity
import com.example.newsapp.models.ArticalUi

fun NewsArticle.toUiModel(): ArticalUi {
    return ArticalUi(
        author = author ?: "",
        title = title,
        urlToImage = urlToImage ?: "",
        publishedAt = publishedAt,
        content = content ?: "",
        isBookMarked = false

        )
}

fun ArticalUi.toEntity(): ArticalEntity {
    return ArticalEntity(
        author, title, urlToImage, publishedAt, content
    )
}

fun ArticalEntity.toarticalui(): ArticalUi {
    return ArticalUi(
        author, title, url, publishedAt, content,true
    )
}
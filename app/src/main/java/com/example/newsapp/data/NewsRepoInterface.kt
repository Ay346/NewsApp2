package com.akshay.newsapp.news.domain

import androidx.lifecycle.LiveData
import com.akshay.newsapp.news.api.NewsService
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.base.RetrofitExecutor
import com.example.newsapp.data.model.Source
import com.example.newsapp.db.ArticalDao
import com.example.newsapp.db.ArticalEntity
import com.example.newsapp.ui.Result
import javax.inject.Inject


interface NewsRepository {

    suspend fun getNewsFromWebservice(country: String, category: String): Result<List<NewsArticle>>
    suspend fun getSource(): Result<List<Source>>
    suspend fun saveArtical(articalEntity: ArticalEntity)
    suspend fun removeArtical(articalEntity: ArticalEntity)
    fun fetAllArtical(): LiveData<List<ArticalEntity>>
    fun getAllArticalUrl(): LiveData<List<String>>
}

class DefaultNewsRepository @Inject constructor(
    private val newsService: NewsService,
    private val retrofitExecutor: RetrofitExecutor,
    private val articalDao: ArticalDao

) : NewsRepository {

    override suspend fun getNewsFromWebservice(
        country: String,
        category: String
    ): Result<List<NewsArticle>> {

        return retrofitExecutor.makeRequest { newsService.getNews(country, category) }
    }

    override suspend fun getSource(): Result<List<Source>> {
        return retrofitExecutor.makeRequest(RetrofitExecutor.SOURCE) { newsService.getSource() }
    }

    override suspend fun saveArtical(articalEntity: ArticalEntity) {
        articalDao.insertArtical(articalEntity)
    }

    override suspend fun removeArtical(articalEntity: ArticalEntity) {
    articalDao.removeartical(articalEntity)
    }

    override fun fetAllArtical(): LiveData<List<ArticalEntity>> {
      return  articalDao.getAllArtical()
    }

    override fun getAllArticalUrl(): LiveData<List<String>> {
        return articalDao.getAllArticalUrl()
    }
}


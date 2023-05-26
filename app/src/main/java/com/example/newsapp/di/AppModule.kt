package com.example.newsapp.di

import android.app.Application
import com.akshay.newsapp.news.api.NewsService
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.akshay.newsapp.news.domain.NewsRepository
import com.example.newsapp.data.base.RetrofitExecutor
import com.example.newsapp.db.ArticalDB
import com.example.newsapp.db.ArticalDao
import com.example.newsapp.ui.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

// another way Service Locator
@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    fun provideRepositry(
        newsService: NewsService,
        retrofitExecutor: RetrofitExecutor,
        articalDao: ArticalDao
    ): NewsRepository {
        return DefaultNewsRepository(
            newsService, retrofitExecutor, articalDao
        )
    }

    @Provides
    fun getRestoreServiceInstance(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Provides
    fun provideDao(db: ArticalDB): ArticalDao {
        return db.articalDao()
    }

}
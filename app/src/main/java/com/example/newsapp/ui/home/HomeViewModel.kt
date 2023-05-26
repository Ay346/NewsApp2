package com.example.newsapp.ui.home

import androidx.lifecycle.*
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.example.newsapp.mapper.toEntity
import com.example.newsapp.mapper.toUiModel
import com.example.newsapp.models.ArticalUi

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.newsapp.ui.base.BaseViewModel

@HiltViewModel
class HomeViewModel @Inject constructor(val repo: DefaultNewsRepository) : BaseViewModel() {
    private val apiList = MutableLiveData<List<ArticalUi>>()
    private val savedList = repo.getAllArticalUrl()
    val articleList = MediatorLiveData<List<ArticalUi>>()

    init {
        loadData()
        articleList.addSource(savedList) {
            mergeArticle(apiList.value, it)

        }
        articleList.addSource(apiList) { articles ->

            mergeArticle(articles, savedList.value)
        }
    }

    fun loadData() {
        viewModelScope.launch {
            handleResult {
                repo.getNewsFromWebservice("us", "technology")
            }?.also { articles ->
                apiList.value = articles.map { item ->
                    item.toUiModel()


                }
            }
        }

//            isLoading.updateValue(true)
//            val response = repo.getNewsFromWebservice("eg")
//            when (response) {
//                is Result.Success -> {
//                   result .value = response.data!!
//                }
//                is Result.Error -> {
//                    errorMsg.updateValue(response.exception.message)
//                }
//                Result.Loading -> {
//                    isLoading.updateValue(false)
//                }
//            }
//            isLoading.updateValue(false)
//        }

    }

    private fun mergeArticle(articles: List<ArticalUi>?, urls: List<String>?) {
        articleList.value = articles?.onEach { item ->
            if (urls?.find { it ->
                    it == item.urlToImage
                } != null
            ) {
                item.isBookMarked = true
            } else {
                item.isBookMarked = false
            }

        }
    }

    fun saveArtical(articalUi: ArticalUi) {
        viewModelScope.launch(Dispatchers.IO) {
            if (articalUi.isBookMarked) {
                repo.removeArtical(articalUi.toEntity())
            }
            repo.saveArtical(articalUi.toEntity())
        }
    }

}
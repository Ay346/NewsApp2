package com.example.newsapp.ui.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.example.newsapp.mapper.toEntity
import com.example.newsapp.models.ArticalUi
import com.example.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel  @Inject constructor(val repo: DefaultNewsRepository) : BaseViewModel() {
    val result = MutableLiveData<List<ArticalUi>>()


    fun saveArtical(articalUi: ArticalUi) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.saveArtical(articalUi.toEntity())
        }
    }
    fun removeArtical(articalUi: ArticalUi) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.removeArtical(articalUi.toEntity())
        }

    }
}
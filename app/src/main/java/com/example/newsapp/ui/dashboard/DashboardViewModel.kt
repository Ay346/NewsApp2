package com.example.newsapp.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.akshay.newsapp.news.domain.DefaultNewsRepository
import com.example.newsapp.mapper.toEntity
import com.example.newsapp.mapper.toarticalui
import com.example.newsapp.models.ArticalUi
import com.example.newsapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(val repo: DefaultNewsRepository) : BaseViewModel() {

    val Result: LiveData<List<ArticalUi>> = repo.fetAllArtical().map {
        it.map { entity ->
            entity.toarticalui()

        }


    }

    fun removeArtical(articalUi: ArticalUi) {
        viewModelScope.launch(Dispatchers.IO) {
            if (articalUi.isBookMarked) {
                repo.removeArtical(articalUi.toEntity())
            }
            repo.saveArtical(articalUi.toEntity())
        }

    }

}
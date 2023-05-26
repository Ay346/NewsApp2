package com.example.newsapp.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsapp.core.updateValue
import com.example.newsapp.event.Event
import  com.example.newsapp.ui.Result

open class BaseViewModel : ViewModel() {
    // event
    val errorMsg = MutableLiveData<Event<String?>>()
    val isLoading = MutableLiveData(Event(false))
    suspend fun <T> handleResult(apiAction: suspend () -> Result<T>): T? {
        isLoading.updateValue(true)
        val response = apiAction()
        var resultData: T? = null
        when (response) {
            is Result.Error -> {
                errorMsg.updateValue(response.exception.message)
            }
            is Result.Success -> {
                resultData = response.data

            }
            Result.Loading -> TODO()
        }
        isLoading.updateValue(false)
        return resultData

    }
}
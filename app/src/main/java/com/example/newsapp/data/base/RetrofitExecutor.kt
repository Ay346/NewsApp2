package com.example.newsapp.data.base

import android.content.Context
import com.sawaf.weatherappex.tools.NetworkHelper
import com.example.newsapp.ui.Result
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

/**
 * Network operation executor for retrofit library
 */
class RetrofitExecutor @Inject constructor(@ApplicationContext private val context: Context) {
    companion object {
        const val NEWS_RESULT = "NEWS"
        const val SOURCE = "SOURCE"
    }

    suspend fun <T> makeRequest(
        requiredResultField: String = NEWS_RESULT,
        call: suspend () -> Response<BaseResponse<T>>,
    ): Result<T> {
        return if (NetworkHelper().isConnected(context)) {
            try {
                val data = safeApiResult(call)
                Timber.i(data.status)
                return when (data.status) {
                    "ok" -> {
                        when (requiredResultField) {
                            NEWS_RESULT -> Result.Success(data.dataNews)
                            SOURCE -> Result.Success(data.dataSource)
                            else -> Result.Error(Exception("Invalid required Feild"))
                        }

                    }
                    "error" -> {
                        Result.Error(Exception("Server error"))
                    }
                    else -> {
                        Result.Error(Exception("Something Wrong Happen"))
                    }
                }
            } catch (e: Exception) {
                Result.Error(e)
            }
        } else {
            Result.Error(Exception("no interent connection"))
        }
    }

    private suspend fun <T : Any> safeApiResult(
        call: suspend () -> Response<T>
    ): T {
        val response = call()
        return when (response.isSuccessful) {
            false -> throw Exception("${response.code()}")
            else -> response.body() ?: throw Exception("Null response")
        }
    }
}
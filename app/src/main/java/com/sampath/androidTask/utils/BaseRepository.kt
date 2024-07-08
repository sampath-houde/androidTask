package com.sampath.androidTask.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import timber.log.Timber

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ) : ApiResponseHandler<T> {
        return withContext(Dispatchers.IO) {
            try {
                ApiResponseHandler.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Timber.e(throwable.toString())
                when(throwable) {

                    is HttpException -> {
                        ApiResponseHandler.Failure(false,
                            throwable.code(),
                            throwable.response()?.errorBody())
                    }

                    else -> {
                        ApiResponseHandler.Failure(true, null, null)
                    }

                }
            }
        }
    }
    
}
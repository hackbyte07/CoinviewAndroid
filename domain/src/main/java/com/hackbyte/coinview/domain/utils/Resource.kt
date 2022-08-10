package com.hackbyte.coinview.domain.utils

sealed class Resource<T>(
    val data: T? = null,
    val isLoading: Boolean = false,
    val error: Throwable? = null
) {
    class Success<T>(data: T?) : Resource<T>(data = data)
    class Loading<T>(isLoading: Boolean = false, data: T? = null) :
        Resource<T>(data = data, isLoading = isLoading)

    class Error<T>(error: Throwable, data: T? = null) : Resource<T>(error = error, data = data)
}

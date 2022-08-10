package com.hackbyte.coinview.data.utils

import timber.log.Timber


inline fun <T> safeCall(action: () -> T): Result<T> {
    return try {
        val result = action()
        Result.success(result)
    } catch (t: Throwable) {
        t.printStackTrace()
        Timber.d(t.message + " Error...")
        Result.failure(t)
    }
}
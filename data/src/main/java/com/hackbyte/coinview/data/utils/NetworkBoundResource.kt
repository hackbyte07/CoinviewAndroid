package com.hackbyte.coinview.data.utils

import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import timber.log.Timber


inline fun <LocalData, RemoteData, ResultData> networkBoundResource(
    crossinline query: () -> Flow<LocalData>,
    crossinline fetchRemoteData: suspend () -> RemoteData,
    crossinline saveRemoteData: suspend (RemoteData) -> Unit,
    crossinline shouldFetchData: (LocalData) -> Boolean = { true },
    crossinline toResultData: LocalData.() -> ResultData
) = flow {
    val data = query.invoke().first()
    val flow = if (shouldFetchData(data)) {
        try {
            emit(Resource.Loading(data = data.toResultData()))

            saveRemoteData(fetchRemoteData())
            query().map {
                Resource.Success(it.toResultData())
            }
        } catch (t: Throwable) {
            t.printStackTrace()
            Timber.e(t.message + " Error")
            query().map {
                Resource.Error(t, it.toResultData())
            }
        }
    } else {
        query().map {
            Resource.Success(it.toResultData())
        }
    }
    emitAll(flow)
}.buffer(capacity = 10).flowOn(Dispatchers.IO)
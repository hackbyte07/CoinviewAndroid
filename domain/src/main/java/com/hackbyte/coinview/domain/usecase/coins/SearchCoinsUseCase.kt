package com.hackbyte.coinview.domain.usecase.coins

import com.hackbyte.coinview.domain.models.search_coins.SearchCoins
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SearchCoinsUseCase(
    private val coinsRepository: CoinsRepository
) {

    suspend operator fun invoke(query: String): Flow<Resource<SearchCoins>> = flow {
        try {
            val inputQuery = query.trim().lowercase()
            if (inputQuery.isBlank() || inputQuery.isEmpty())
                throw IllegalArgumentException("Empty query")
            emit(Resource.Loading(true))
            val data = coinsRepository.searchCoins(inputQuery).getOrThrow()
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        } catch (e: IllegalArgumentException) {
            emit(Resource.Error(e))
        } finally {
            emit(Resource.Loading(false))
        }
    }.flowOn(Dispatchers.IO)


}
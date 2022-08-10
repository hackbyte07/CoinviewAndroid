package com.hackbyte.coinview.domain.usecase.coins

import com.hackbyte.coinview.domain.models.coins.coins_list.CoinsList
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class CoinsListUseCase(
    private val coinsRepository: CoinsRepository
) {

    suspend operator fun invoke(): Flow<Resource<CoinsList>> = flow {
        emit(Resource.Loading(true))
        try {
            val data = coinsRepository.coinsList().getOrThrow()
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        } finally {
            emit(Resource.Loading(false))
        }
    }.flowOn(Dispatchers.IO)

}
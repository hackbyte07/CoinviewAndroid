package com.hackbyte.coinview.domain.usecase.coins

import com.hackbyte.coinview.domain.models.coins.coins_id_market_chart.CoinsIdMarketChart
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CoinMarketChartUseCase(
    private val coinsRepository: CoinsRepository
) {

    suspend operator fun invoke(
        id: String,
        currency: String,
        days: Int
    ): Flow<Resource<CoinsIdMarketChart>> = flow {
        emit(Resource.Loading(true))
        try {
            val data = coinsRepository.coinMarketChart(id, currency, days).getOrThrow()
            emit(Resource.Success(data))
        } catch (t: Throwable) {
            emit(Resource.Error(t))
        } finally {
            emit(Resource.Loading(false))
        }
    }.flowOn(Dispatchers.IO)
}
package com.hackbyte.coinview.domain.usecase.exchange

import com.hackbyte.coinview.domain.models.exchange.ExchangeItem
import com.hackbyte.coinview.domain.repository.ExchangeRepository
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ExchangesUseCase(private val exchangeRepository: ExchangeRepository) {
    suspend operator fun invoke(): Flow<Resource<List<ExchangeItem>>> = flow {
        emit(Resource.Loading(isLoading = true))
        try {
            val data = exchangeRepository.getExchanges().getOrThrow()
            emit(Resource.Success(data))
        } catch (e: Exception) {
            emit(Resource.Error(e))
        } finally {
            emit(Resource.Loading(false))
        }
    }
}
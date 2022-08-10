package com.hackbyte.coinview.data.remote.exchange

import coingecko.CoinGeckoClient
import com.hackbyte.coinview.data.mapper.toExchangesItem
import com.hackbyte.coinview.data.utils.safeCall
import com.hackbyte.coinview.domain.models.exchange.ExchangeItem
import com.hackbyte.coinview.domain.repository.ExchangeRepository
import timber.log.Timber

class ExchangeRepositoryImpl(
    private val coinGeckoClient: CoinGeckoClient
) : ExchangeRepository {

    override suspend fun getExchanges(): Result<List<ExchangeItem>> = safeCall {
        Timber.d("getting exchanges data")
        val data = coinGeckoClient.getExchanges(page = 1, perPage = 50).exchanges
        data.toExchangesItem()
    }
}
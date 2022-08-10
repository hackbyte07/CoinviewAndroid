package com.hackbyte.coinview.domain.repository

import com.hackbyte.coinview.domain.models.exchange.ExchangeItem

interface ExchangeRepository {

    suspend fun getExchanges(): Result<List<ExchangeItem>>

}
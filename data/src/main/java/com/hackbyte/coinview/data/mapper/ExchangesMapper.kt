package com.hackbyte.coinview.data.mapper

import coingecko.models.exchanges.Exchange
import com.hackbyte.coinview.domain.models.exchange.ExchangeItem


fun List<Exchange>.toExchangesItem(): List<ExchangeItem> = map {
    ExchangeItem(
        it.country,
        it.description ?: "",
        it.hasTradingIncentive,
        it.id,
        it.image ?: "",
        it.name,
        it.tradeVolume24hBtc,
        it.tradeVolume24hBtc,
        trust_score = 0,
        trust_score_rank = 0,
        it.url ?: "",
        it.yearEstablished.toInt()
    )
}
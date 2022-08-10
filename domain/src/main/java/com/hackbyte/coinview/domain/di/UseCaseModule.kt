package com.hackbyte.coinview.domain.di

import com.hackbyte.coinview.domain.usecase.coins.*
import com.hackbyte.coinview.domain.usecase.exchange.ExchangeUseCase
import com.hackbyte.coinview.domain.usecase.exchange.ExchangesUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val coinsUseCaseModule = module {
    singleOf(::CoinInfoByIdUseCase)
    singleOf(::CoinMarketChartUseCase)
    singleOf(::CoinsListUseCase)
    singleOf(::SearchCoinsUseCase)
    singleOf(::CoinsUseCase)
    singleOf(::ExchangesUseCase)
    singleOf(::ExchangeUseCase)
}
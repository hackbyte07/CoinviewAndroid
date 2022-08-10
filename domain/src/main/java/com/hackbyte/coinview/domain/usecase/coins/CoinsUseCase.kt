package com.hackbyte.coinview.domain.usecase.coins

data class CoinsUseCase(
    val coinInfoByIdUseCase: CoinInfoByIdUseCase,
    val coinMarketChartUseCase: CoinMarketChartUseCase,
    val coinsListUseCase: CoinsListUseCase,
    val searchCoinsUseCase: SearchCoinsUseCase
)

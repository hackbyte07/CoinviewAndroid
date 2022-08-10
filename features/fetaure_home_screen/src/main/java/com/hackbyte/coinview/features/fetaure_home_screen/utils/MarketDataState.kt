package com.hackbyte.coinview.features.fetaure_home_screen.utils

import com.hackbyte.coinview.domain.models.coins.coins_market.CoinsMarket

data class MarketDataState(
    val loading: Boolean = false,
    val data: CoinsMarket = CoinsMarket(emptyList()),
    val error: String = "",
)

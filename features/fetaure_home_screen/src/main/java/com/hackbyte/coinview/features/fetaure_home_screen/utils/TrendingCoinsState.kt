package com.hackbyte.coinview.features.fetaure_home_screen.utils

import com.hackbyte.coinview.domain.models.trending_coins.TrendingCoins

data class TrendingCoinsState(
    val data: TrendingCoins = TrendingCoins(emptyList()),
    val error: String = "",
    val isLoading: Boolean = false
)

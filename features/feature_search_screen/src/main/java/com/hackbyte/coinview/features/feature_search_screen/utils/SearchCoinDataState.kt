package com.hackbyte.coinview.features.feature_search_screen.utils

import com.hackbyte.coinview.domain.models.search_coins.SearchCoins

data class SearchCoinDataState(
    val data: SearchCoins = SearchCoins(emptyList(), emptyList(), emptyList(), emptyList()),
    val error: String = "",
    val isLoading: Boolean = false
)

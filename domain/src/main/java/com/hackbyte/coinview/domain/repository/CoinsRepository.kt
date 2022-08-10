package com.hackbyte.coinview.domain.repository

import com.hackbyte.coinview.domain.models.coins.coins_id.CoinsId
import com.hackbyte.coinview.domain.models.coins.coins_id_market_chart.CoinsIdMarketChart
import com.hackbyte.coinview.domain.models.coins.coins_list.CoinsList
import com.hackbyte.coinview.domain.models.coins.coins_market.CoinsMarket
import com.hackbyte.coinview.domain.models.search_coins.SearchCoins
import com.hackbyte.coinview.domain.models.trending_coins.TrendingCoins
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {

    suspend fun coinInfoById(id: String): Result<CoinsId>

    suspend fun coinMarketChart(id: String, currency: String, days: Int): Result<CoinsIdMarketChart>

    suspend fun coinsList(): Result<CoinsList>

    suspend fun coinsMarket(
        currency: String,
        orderBy: String,
        perPageItems: Int,
        page: Int
    ): Flow<Resource<CoinsMarket>>


    suspend fun trendingCoins(): Flow<Resource<TrendingCoins>>

    suspend fun searchCoins(query: String): Result<SearchCoins>
}
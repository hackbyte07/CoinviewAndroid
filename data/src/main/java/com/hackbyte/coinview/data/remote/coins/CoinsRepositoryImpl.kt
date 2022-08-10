package com.hackbyte.coinview.data.remote.coins

import androidx.room.withTransaction
import coingecko.CoinGeckoClient
import com.hackbyte.coinview.data.local.CoinsDatabase
import com.hackbyte.coinview.data.local.coins.coins_market.CoinsMarketItemLocal
import com.hackbyte.coinview.data.local.coins.trending_coins.ItemLocal
import com.hackbyte.coinview.data.mapper.toCoinsId
import com.hackbyte.coinview.data.mapper.toCoinsMarket
import com.hackbyte.coinview.data.mapper.toSearchCoins
import com.hackbyte.coinview.data.mapper.toTrendingCoins
import com.hackbyte.coinview.data.remote.coins.search_coins.Constants
import com.hackbyte.coinview.data.remote.coins.search_coins.model.SearchCoinsDto
import com.hackbyte.coinview.data.utils.networkBoundResource
import com.hackbyte.coinview.data.utils.safeCall
import com.hackbyte.coinview.domain.models.coins.coins_id.CoinsId
import com.hackbyte.coinview.domain.models.coins.coins_id_market_chart.CoinsIdMarketChart
import com.hackbyte.coinview.domain.models.coins.coins_list.CoinsList
import com.hackbyte.coinview.domain.models.coins.coins_market.CoinsMarket
import com.hackbyte.coinview.domain.models.search_coins.SearchCoins
import com.hackbyte.coinview.domain.models.trending_coins.TrendingCoins
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.utils.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl(
    private val coinGeckoClient: CoinGeckoClient,
    private val db: CoinsDatabase,
    private val client: HttpClient
) : CoinsRepository {

    private val trendingCoinsDao = db.getTrendingCoinsDao()
    private val coinsMarketDao = db.getCoinsMarketDao()

    override suspend fun coinInfoById(id: String): Result<CoinsId> = safeCall {
        val data = coinGeckoClient.getCoinById(
            id,
            localization = false,
            tickers = true,
            marketData = false,
            communityData = false,
            developerData = false,
            sparkline = false
        )
        data.toCoinsId()
    }

    override suspend fun coinMarketChart(
        id: String,
        currency: String,
        days: Int
    ): Result<CoinsIdMarketChart> {
        TODO("Not yet implemented")
    }

    override suspend fun coinsList(): Result<CoinsList> {
        TODO("Not yet implemented")
    }

    override suspend fun coinsMarket(
        currency: String,
        orderBy: String,
        perPageItems: Int,
        page: Int
    ): Flow<Resource<CoinsMarket>> = networkBoundResource(
        query = {
            coinsMarketDao.getAll()
        },
        fetchRemoteData = {
            coinGeckoClient.getCoinMarkets(
                vsCurrency = currency,
                order = orderBy,
                perPage = perPageItems,
                page = page
            )
        },
        saveRemoteData = {
            db.withTransaction {
                if (page == 1) {
                    coinsMarketDao.deleteAll()
                }
                it.markets.forEach {
                    coinsMarketDao.insert(
                        CoinsMarketItemLocal(
                            it.currentPrice,
                            it.id,
                            it.image ?: "",
                            it.lastUpdated ?: "",
                            it.marketCapRank.toInt(),
                            it.name,
                            it.priceChange24h,
                            it.priceChangePercentage24h,
                            it.symbol
                        )
                    )
                }
            }
        },
        toResultData = {
            toCoinsMarket()
        }
    )

    override suspend fun trendingCoins(): Flow<Resource<TrendingCoins>> = networkBoundResource(
        query = {
            trendingCoinsDao.getAll()
        },
        fetchRemoteData = {
            coinGeckoClient.getTrending()
        },
        saveRemoteData = {
            db.withTransaction {
                trendingCoinsDao.deleteAll()
                it.coins.forEach {
                    trendingCoinsDao.insert(
                        ItemLocal(
                            coin_id = it.item.coinId,
                            large = it.item.large,
                            id = it.item.id,
                            market_cap_rank = it.item.marketCapRank,
                            name = it.item.name,
                            price_btc = it.item.priceBtc,
                            score = it.item.priceBtc.toInt(),
                            slug = it.item.slug,
                            small = it.item.small,
                            symbol = it.item.symbol,
                            thumb = it.item.thumb
                        )
                    )
                }
            }
        },
        toResultData = {
            toTrendingCoins()
        }
    )

    override suspend fun searchCoins(query: String): Result<SearchCoins> = safeCall {
        val data = client.get(Constants.SEARCH) {
            url {
                parameters.append("query", query)
            }
        }.body() as SearchCoinsDto
        data.toSearchCoins()
    }
}
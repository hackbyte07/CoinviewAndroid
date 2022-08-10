package com.hackbyte.coinview.domain.models.coins.coins_id

data class CoinsId(

    val block_time_in_minutes: Int, // 10
    val categories: List<String>,
    val coingecko_rank: Int, // 1
    val coingecko_score: Double, // 82.927
    val community_score: Double, // 82.845
    val description: Description,
    val id: String, // bitcoin
    val image: Image,
    val last_updated: String, // 2022-08-04T15:26:14.935Z
    val liquidity_score: Double, // 99.754
    val market_cap_rank: Int, // 1
    val name: String, // Bitcoin
    val symbol: String, // btc
    val tickers: List<Ticker>
)
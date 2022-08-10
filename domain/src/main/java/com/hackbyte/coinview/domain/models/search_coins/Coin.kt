package com.hackbyte.coinview.domain.models.search_coins

data class Coin(
    val id: String, // bitcoin
    val large: String, // https://assets.coingecko.com/coins/images/1/large/bitcoin.png
    val market_cap_rank: Int, // 1
    val name: String, // Bitcoin
    val symbol: String, // BTC
    val thumb: String // https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png
)
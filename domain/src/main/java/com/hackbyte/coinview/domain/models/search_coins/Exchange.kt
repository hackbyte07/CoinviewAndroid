package com.hackbyte.coinview.domain.models.search_coins

data class Exchange(
    val id: String, // hitbtc
    val large: String, // https://assets.coingecko.com/markets/images/24/large/hitbtc.png
    val market_type: String, // spot
    val name: String, // HitBTC
    val thumb: String // https://assets.coingecko.com/markets/images/24/thumb/hitbtc.png
)
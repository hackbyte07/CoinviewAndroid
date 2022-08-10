package com.hackbyte.coinview.domain.models.exchange

data class ExchangeItem(
    val country: String?, // Cayman Islands
    val description: String,
    val has_trading_incentive: Boolean, // false
    val id: String, // binance
    val image: String, // httpfilles://assets.coingecko.com/markets/images/52/small/binance.jpg?1519353250
    val name: String, // Binance
    val trade_volume_24h_btc: Double, // 658524.5006616401
    val trade_volume_24h_btc_normalized: Double, // 658524.5006616401
    val trust_score: Int, // 10
    val trust_score_rank: Int, // 1
    val url: String, // https://www.binance.com/
    val year_established: Int? // 2017
)
package com.hackbyte.coinview.domain.models.coins.coins_id

data class Ticker(
    val base: String, // BTC
    val bid_ask_spread_percentage: Double, // 0.015067
    val coin_id: String, // bitcoin
    val converted_last: ConvertedLast,
    val converted_volume: ConvertedVolume,
    val is_anomaly: Boolean, // false
    val is_stale: Boolean, // false
    val last: Double, // 22897.48
    val last_fetch_at: String, // 2022-08-04T15:19:49+00:00
    val last_traded_at: String, // 2022-08-04T15:19:49+00:00
    val market: Market,
    val target: String, // USDT
    val target_coin_id: String, // tether
    val timestamp: String, // 2022-08-04T15:19:49+00:00
    val trade_url: String, // https://www.binance.com/en/trade/BTC_USDT?ref=37754157
    val trust_score: String, // green
    val volume: Double // 151015.02868504947
)
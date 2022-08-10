package com.hackbyte.coinview.domain.models.coins.coins_id_market_chart

data class CoinsIdMarketChart(
    val market_caps: List<List<Double>>,
    val prices: List<List<Double>>,
    val total_volumes: List<List<Double>>
)
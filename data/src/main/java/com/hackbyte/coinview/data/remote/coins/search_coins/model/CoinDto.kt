package com.hackbyte.coinview.data.remote.coins.search_coins.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinDto(
    @SerialName("id")
    val id: String, // bitcoin
    @SerialName("large")
    val large: String, // https://assets.coingecko.com/coins/images/1/large/bitcoin.png
    @SerialName("market_cap_rank")
    val marketCapRank: Int?, // 1
    @SerialName("name")
    val name: String, // Bitcoin
    @SerialName("symbol")
    val symbol: String, // BTC
    @SerialName("thumb")
    val thumb: String // https://assets.coingecko.com/coins/images/1/thumb/bitcoin.png
)
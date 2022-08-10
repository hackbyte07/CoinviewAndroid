package com.hackbyte.coinview.data.remote.coins.search_coins.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeDto(
    @SerialName("id")
    val id: String, // mercado_bitcoin
    @SerialName("large")
    val large: String, // https://assets.coingecko.com/markets/images/34/large/logo_MB_hexagono.png
    @SerialName("market_type")
    val marketType: String, // spot
    @SerialName("name")
    val name: String, // Mercado Bitcoin
    @SerialName("thumb")
    val thumb: String // https://assets.coingecko.com/markets/images/34/thumb/logo_MB_hexagono.png
)
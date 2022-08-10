package com.hackbyte.coinview.data.remote.coins.search_coins.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCoinsDto(
    @SerialName("categories")
    val categories: List<CategoryDto>,
    @SerialName("coins")
    val coins: List<CoinDto>,
    @SerialName("exchanges")
    val exchanges: List<ExchangeDto>,
    @SerialName("nfts")
    val nfts: List<NftDto>
)
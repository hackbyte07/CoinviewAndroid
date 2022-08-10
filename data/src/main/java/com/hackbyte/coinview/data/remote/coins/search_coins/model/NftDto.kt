package com.hackbyte.coinview.data.remote.coins.search_coins.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NftDto(
    @SerialName("id")
    val id: String?, // cryptozombiez
    @SerialName("name")
    val name: String, // CryptoZombiez
    @SerialName("symbol")
    val symbol: String, // ZOMBIE
    @SerialName("thumb")
    val thumb: String // https://assets.coingecko.com/nft_contracts/images/193/thumb/-GnUxUz1_400x400.png
)
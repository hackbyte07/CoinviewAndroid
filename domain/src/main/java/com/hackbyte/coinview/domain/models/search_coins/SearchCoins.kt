package com.hackbyte.coinview.domain.models.search_coins

data class SearchCoins(
    val categories: List<Category>,
    val coins: List<Coin>,
    val exchanges: List<Exchange>,
    val nfts: List<Nft>
)
package com.hackbyte.coinview.data.mapper

import com.hackbyte.coinview.data.remote.coins.search_coins.model.SearchCoinsDto
import com.hackbyte.coinview.domain.models.search_coins.*


fun SearchCoinsDto.toSearchCoins(): SearchCoins = SearchCoins(
    categories = categories.map {
        Category(it.id, it.name)
    },
    coins = coins.map {
        Coin(it.id, it.large, it.marketCapRank ?: 0, it.name, it.symbol, it.thumb)
    },
    exchanges = exchanges.map {
        Exchange(it.id, it.large, it.marketType, it.name, it.thumb)
    },
    nfts = nfts.map {
        Nft(it.id ?: "", it.name, it.symbol, it.thumb)
    }
)
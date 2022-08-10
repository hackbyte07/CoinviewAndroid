package com.hackbyte.coinview.data.mapper

import com.hackbyte.coinview.data.local.coins.trending_coins.ItemLocal
import com.hackbyte.coinview.domain.models.trending_coins.Coin
import com.hackbyte.coinview.domain.models.trending_coins.Item
import com.hackbyte.coinview.domain.models.trending_coins.TrendingCoins


fun List<ItemLocal>.toTrendingCoins(): TrendingCoins = TrendingCoins(
    this.map {
        Coin(
            item = Item(
                coin_id = it.coin_id,
                large = it.large,
                id = it.id,
                market_cap_rank = it.market_cap_rank,
                name = it.name,
                price_btc = it.price_btc,
                score = it.price_btc.toInt(),
                slug = it.slug,
                small = it.small,
                symbol = it.symbol,
                thumb = it.thumb
            )
        )
    }
)
package com.hackbyte.coinview.data.mapper

import com.hackbyte.coinview.data.local.coins.coins_market.CoinsMarketItemLocal
import com.hackbyte.coinview.domain.models.coins.coins_market.CoinsMarket
import com.hackbyte.coinview.domain.models.coins.coins_market.CoinsMarketItem


fun List<CoinsMarketItemLocal>.toCoinsMarket(): CoinsMarket = CoinsMarket(
    coinsMarketItem = map {
        CoinsMarketItem(
            it.current_price, it.id, it.image, it.last_updated, it.market_cap_rank, it.name,
            it.price_change_24h, it.price_change_percentage_24h, it.symbol
        )
    }
)
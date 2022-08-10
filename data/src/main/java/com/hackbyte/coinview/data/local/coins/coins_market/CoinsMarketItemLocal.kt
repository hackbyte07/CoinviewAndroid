package com.hackbyte.coinview.data.local.coins.coins_market

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coins_market_item")
data class CoinsMarketItemLocal(
    val current_price: Double, // 1476.33
    @PrimaryKey
    val id: String, // bitcoin
    val image: String, // https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1547033579
    val last_updated: String, // 2022-07-27T13:44:11.679Z
    val market_cap_rank: Int, // 1
    val name: String, // Bitcoin
    val price_change_24h: Double, // 372.12
    val price_change_percentage_24h: Double, // 1.77355
    val symbol: String, // btc
)
package com.hackbyte.coinview.data.local.coins.trending_coins

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "item_local")
data class ItemLocal(
    val coin_id: Int, // 25244
    @PrimaryKey
    val id: String, // optimism
    val large: String, // https://assets.coingecko.com/coins/images/25244/large/OP.jpeg?1651026279
    val market_cap_rank: Int, // 122
    val name: String, // Optimism
    val price_btc: Double, // 6.97654695703429e-05
    val score: Int, // 0
    val slug: String, // optimism
    val small: String, // https://assets.coingecko.com/coins/images/25244/small/OP.jpeg?1651026279
    val symbol: String, // OP
    val thumb: String // https://assets.coingecko.com/coins/images/25244/thumb/OP.jpeg?1651026279
)
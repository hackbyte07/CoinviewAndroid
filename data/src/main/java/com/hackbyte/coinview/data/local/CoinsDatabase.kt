package com.hackbyte.coinview.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hackbyte.coinview.data.local.coins.coins_market.CoinsMarketDao
import com.hackbyte.coinview.data.local.coins.coins_market.CoinsMarketItemLocal
import com.hackbyte.coinview.data.local.coins.trending_coins.ItemLocal
import com.hackbyte.coinview.data.local.coins.trending_coins.TrendingCoinsDao


@Database(
    entities = [ItemLocal::class, CoinsMarketItemLocal::class],
    version = 1,
    exportSchema = false
)
abstract class CoinsDatabase : RoomDatabase() {

    abstract fun getTrendingCoinsDao(): TrendingCoinsDao
    abstract fun getCoinsMarketDao(): CoinsMarketDao

}
package com.hackbyte.coinview.data.local.coins.coins_market

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface CoinsMarketDao {

    @Transaction
    @Insert
    suspend fun insert(coinsMarketItemLocal: CoinsMarketItemLocal)

    @Transaction
    @Query("delete from coins_market_item")
    suspend fun deleteAll()

    @Transaction
    @Query("select * from coins_market_item")
    fun getAll(): Flow<List<CoinsMarketItemLocal>>

}
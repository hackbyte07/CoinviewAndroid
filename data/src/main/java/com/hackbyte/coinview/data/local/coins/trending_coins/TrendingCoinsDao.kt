package com.hackbyte.coinview.data.local.coins.trending_coins

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow


@Dao
interface TrendingCoinsDao {

    @Transaction
    @Insert
    suspend fun insert(itemLocal: ItemLocal)

    @Transaction
    @Query("delete from item_local")
    suspend fun deleteAll()

    @Transaction
    @Query("select * from item_local")
    fun getAll(): Flow<List<ItemLocal>>

}
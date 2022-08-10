package com.hackbyte.coinview.data.remote.coins.search_coins.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("id")
    val id: Int, // 1
    @SerialName("name")
    val name: String // Artificial Intelligence
)
package com.hackbyte.coinview.features.feature_detail_screen.utils

import com.hackbyte.coinview.domain.models.coins.coins_id.CoinsId
import com.hackbyte.coinview.domain.models.coins.coins_id.Description
import com.hackbyte.coinview.domain.models.coins.coins_id.Image

data class CoinsDetailState(
    val loading: Boolean = false,
    val coinDetails: CoinsId = CoinsId(
        0, emptyList(), 0, 0.0,
        0.0, Description(emptyList()), "", Image("", "", ""), "", 0.0, 0,
        "", "", emptyList()
    ),
    val error: String = "",
    var isRefreshing: Boolean = false


)

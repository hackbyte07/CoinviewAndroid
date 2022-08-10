package com.hackbyte.coinview.features.feature_exchange_screen.utils

import com.hackbyte.coinview.domain.models.exchange.ExchangeItem

data class ExchangeDataState(
    val data: List<ExchangeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)

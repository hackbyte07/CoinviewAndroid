package com.hackbyte.coinview.features.common.network_manager

sealed class NetworkStatus(val message: String) {
    object Available : NetworkStatus("Network available")
    object Unavailable : NetworkStatus("Network unavailable")
}


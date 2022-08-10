package com.hackbyte.coinview.features.common.network_manager

import kotlinx.coroutines.flow.Flow


interface NetworkManager {


    fun getNetworkStatus(): Flow<NetworkStatus>

}
package com.hackbyte.coinview.features.common.network_manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flowOn

class NetworkManagerImpl(context: Context) : NetworkManager {

    private val networkService =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    override fun getNetworkStatus(): Flow<NetworkStatus> = callbackFlow<NetworkStatus> {
        val networkStatusCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                trySendBlocking(NetworkStatus.Available)
            }

            override fun onUnavailable() {
                super.onUnavailable()
                trySendBlocking(NetworkStatus.Unavailable)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                trySendBlocking(NetworkStatus.Unavailable)
            }
        }
        val request = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()
        networkService.registerNetworkCallback(request, networkStatusCallback)
        awaitClose {
            networkService.unregisterNetworkCallback(networkStatusCallback)
        }
    }.flowOn(Dispatchers.IO)
}
package com.hackbyte.coinview.features.feature_exchange_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.usecase.exchange.ExchangeUseCase
import com.hackbyte.coinview.domain.utils.Resource
import com.hackbyte.coinview.features.common.network_manager.NetworkManager
import com.hackbyte.coinview.features.common.network_manager.NetworkStatus
import com.hackbyte.coinview.features.feature_exchange_screen.utils.ExchangeDataState
import com.hackbyte.coinview.features.feature_exchange_screen.utils.NetworkState
import kotlinx.coroutines.launch
import timber.log.Timber

class ExchangeViewModel(
    private val exchangeUseCase: ExchangeUseCase,
    private val networkManager: NetworkManager
) : ViewModel() {

    var exchangesState by mutableStateOf(ExchangeDataState())
    var networkState by mutableStateOf(NetworkState(false))

    init {
        getNetworkStatus()
        getExchangesData()
    }

    private fun getNetworkStatus() {
        viewModelScope.launch {
            networkManager.getNetworkStatus().collect {
                networkState = when (it) {
                    NetworkStatus.Available -> {
                        Timber.tag("bye").d(it.message)
                        networkState.copy(hasNetwork = true)
                    }
                    NetworkStatus.Unavailable -> {
                        Timber.tag("bye").d(it.message)
                        networkState.copy(hasNetwork = false)
                    }
                }
            }
        }
    }

    private fun getExchangesData() {
        viewModelScope.launch {
            exchangeUseCase.exchangesUseCase.invoke().collect {
                exchangesState = when (it) {
                    is Resource.Error -> exchangesState.copy(
                        error = it.error?.message ?: "Unknown error"
                    )
                    is Resource.Loading -> exchangesState.copy(isLoading = it.isLoading)
                    is Resource.Success -> exchangesState.copy(data = it.data!!)
                }
            }
        }
    }

}
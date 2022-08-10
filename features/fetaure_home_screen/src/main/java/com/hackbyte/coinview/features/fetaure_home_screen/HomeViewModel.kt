package com.hackbyte.coinview.features.fetaure_home_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.utils.Resource
import com.hackbyte.coinview.features.common.datastore.PrefDataStoreRepository
import com.hackbyte.coinview.features.common.network_manager.NetworkManager
import com.hackbyte.coinview.features.common.network_manager.NetworkStatus
import com.hackbyte.coinview.features.fetaure_home_screen.utils.NetworkState
import com.hackbyte.coinview.features.fetaure_home_screen.utils.TrendingCoinsState
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val coinsRepository: CoinsRepository,
    private val prefDataStoreRepository: PrefDataStoreRepository,
    private val networkManager: NetworkManager
) : ViewModel() {

    var trendingCoinsState by mutableStateOf(TrendingCoinsState())
        private set

    var userName by mutableStateOf("Guest")
        private set
    var networkState by mutableStateOf(NetworkState(hasNetwork = false))
        private set

    private var currency = "usd"

    private var marketDataPage by mutableStateOf(1)

    var marketDataState by mutableStateOf(com.hackbyte.coinview.features.fetaure_home_screen.utils.MarketDataState())
        private set

    init {
        Timber.tag("viewModel").d("creating home viewModel...")
        netWorkStatus()
        updateUser()
        getHomeViewData()
    }

    private fun updateUser() {
        viewModelScope.launch {

            prefDataStoreRepository.readUsernameFromDataStore.collect {
                Timber.tag("username").d("$it hello")
                userName = it
                return@collect
            }
        }
    }

    private fun netWorkStatus() = viewModelScope.launch {
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

    fun logout() {
        viewModelScope.launch {
            prefDataStoreRepository.saveAuthTokenToDataStore("")
        }
    }


    fun getMarkets() {
        Timber.d("current page is $marketDataPage")

        viewModelScope.launch {
            launch {
                prefDataStoreRepository.readCurrencyFromDataStore.collect {
                    currency = it
                    return@collect
                }
            }
            launch {
                Timber.d("getMarkets Request Started")
                coinsRepository.coinsMarket(
                    currency = currency, orderBy = "market_cap_desc",
                    perPageItems = 100, page = marketDataPage
                ).collect {
                    marketDataState = when (it) {
                        is Resource.Error -> marketDataState.copy(
                            error = it.error?.message ?: "Unknown Error",
                            data = it.data!!
                        )
                        is Resource.Loading -> marketDataState.copy(
                            loading = it.isLoading,
                            data = it.data!!
                        )
                        is Resource.Success -> {
                            marketDataPage++
                            marketDataState.copy(data = it.data!!)
                        }
                    }
                }
            }
        }
    }


    private fun getHomeViewData() {
        Timber.d("calling viewmodel again ")
        viewModelScope.launch {
            launch {
                prefDataStoreRepository.readCurrencyFromDataStore.collect {
                    currency = it
                    return@collect
                }
            }
            launch {
                coinsRepository.trendingCoins().collect {
                    trendingCoinsState = when (it) {
                        is Resource.Error -> trendingCoinsState.copy(
                            error = it.error?.message ?: "Unknown Error", data = it.data!!
                        )
                        is Resource.Loading -> trendingCoinsState.copy(
                            isLoading = it.isLoading,
                            data = it.data!!
                        )
                        is Resource.Success -> trendingCoinsState.copy(data = it.data!!)
                    }
                }
            }

        }
    }
}
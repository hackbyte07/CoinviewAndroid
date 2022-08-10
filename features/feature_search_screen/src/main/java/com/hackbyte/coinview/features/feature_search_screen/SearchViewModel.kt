package com.hackbyte.coinview.features.feature_search_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.usecase.coins.CoinsUseCase
import com.hackbyte.coinview.domain.utils.Resource
import com.hackbyte.coinview.features.common.datastore.PrefDataStoreRepository
import com.hackbyte.coinview.features.common.network_manager.NetworkManager
import com.hackbyte.coinview.features.common.network_manager.NetworkStatus
import com.hackbyte.coinview.features.feature_search_screen.utils.NetworkState
import com.hackbyte.coinview.features.feature_search_screen.utils.Search
import com.hackbyte.coinview.features.feature_search_screen.utils.SearchCoinDataState
import kotlinx.coroutines.launch
import timber.log.Timber


class SearchViewModel(
    private val coinsUseCase: CoinsUseCase,
    private val coinsRepository: CoinsRepository,
    private val prefDataStoreRepository: PrefDataStoreRepository,
    private val networkManager: NetworkManager
) : ViewModel() {


    var networkState by mutableStateOf(NetworkState(false))

    var search by mutableStateOf(Search.FALSE)
        private set

    fun setSearch() {
        search = Search.FALSE
    }

    init {
        Timber.tag("viewModel").d("creating search viewModel...")
        getNetworkStatus()
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


    /*******************************************************************************/


    var searchCoinsDataState by mutableStateOf(SearchCoinDataState())
        private set

    fun searchCoins(query: String) {
        viewModelScope.launch {
            coinsUseCase.searchCoinsUseCase.invoke(query).collect {
                searchCoinsDataState = when (it) {
                    is Resource.Error -> searchCoinsDataState.copy(
                        error = it.error?.message ?: "Unknown Error"
                    )
                    is Resource.Loading -> searchCoinsDataState.copy(isLoading = it.isLoading)
                    is Resource.Success -> {
                        search = Search.TRUE
                        searchCoinsDataState.copy(data = it.data!!)
                    }
                }
            }
        }
    }
}



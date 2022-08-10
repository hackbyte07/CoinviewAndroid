package com.hackbyte.coinview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.features.common.datastore.PrefDataStoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch


class MainViewModel(
    private val prefDataStoreRepository: PrefDataStoreRepository
) : ViewModel() {

    val showSplashScreen = MutableStateFlow(true)
    var token by mutableStateOf("")
        private set

    init {
        authToken()
    }

    private fun authToken() {
        viewModelScope.launch {
            prefDataStoreRepository.readAuthTokenFromDataStore.collect {
                token = it
                showSplashScreen.value = false
                return@collect
            }
        }
    }

}
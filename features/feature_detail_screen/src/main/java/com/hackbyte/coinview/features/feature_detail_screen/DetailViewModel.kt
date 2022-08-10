package com.hackbyte.coinview.features.feature_detail_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.usecase.coins.CoinsUseCase
import com.hackbyte.coinview.domain.utils.Resource
import com.hackbyte.coinview.features.feature_detail_screen.utils.CoinsDetailState
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailViewModel(
    private val coinsUseCase: CoinsUseCase
) : ViewModel() {

    init {
        Timber.tag("viewModel").d("creating detail viewModel...")
    }

    var coinsDetailState by mutableStateOf(CoinsDetailState())
        private set

    fun getCoinsDetail(id: String) {
        viewModelScope.launch {
            coinsUseCase.coinInfoByIdUseCase.invoke(id).collect {
                coinsDetailState = when (it) {
                    is Resource.Error -> coinsDetailState.copy(
                        error = it.error?.message ?: "Unknown error"
                    )
                    is Resource.Loading -> coinsDetailState.copy(loading = it.isLoading)
                    is Resource.Success -> coinsDetailState.copy(coinDetails = it.data!!)
                }
            }
        }
    }

}
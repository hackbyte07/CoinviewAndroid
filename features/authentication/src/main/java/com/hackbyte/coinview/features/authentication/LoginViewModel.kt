package com.hackbyte.coinview.features.authentication

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.repository.AuthenticationRepository
import com.hackbyte.coinview.domain.utils.Resource
import com.hackbyte.coinview.features.common.datastore.PrefDataStoreRepository
import kotlinx.coroutines.launch
import timber.log.Timber

class LoginViewModel(
    private val authenticationRepository: AuthenticationRepository,
    private val prefDataStoreRepository: PrefDataStoreRepository
) : ViewModel() {

    init {
        Timber.tag("viewModel").d("creating login viewModel...")
    }

    var isLoading by mutableStateOf(false)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set


    fun signIn(username: String, password: String) {
        val inputUsername = username.trim()
        val inputPassword = password.trim()
        if (inputUsername.isBlank() || inputUsername.isBlank() || inputUsername.length < 6 ||
            inputPassword.isBlank() || inputPassword.isEmpty() || inputPassword.length < 6
        ) {
            return
        }
        viewModelScope.launch {
            prefDataStoreRepository.saveUsernameToDataStore(inputUsername)
            authenticationRepository.signIn(inputUsername, inputPassword).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = it.isLoading
                    }
                    is Resource.Success -> {
                        prefDataStoreRepository.saveAuthTokenToDataStore(it.data?.token ?: "")
                        loginSuccess = true
                    }
                    is Resource.Error -> {
                        errorMessage = it.error?.message ?: "Unknown error"
                    }
                }
            }
        }

    }

}
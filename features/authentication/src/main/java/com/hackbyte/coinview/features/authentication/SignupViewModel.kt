package com.hackbyte.coinview.features.authentication

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hackbyte.coinview.domain.repository.AuthenticationRepository
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.withContext
import timber.log.Timber

class SignupViewModel(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {


    init {
        Timber.tag("viewModel").d("creating signup viewModel...")
    }

    var isLoading by mutableStateOf(false)
        private set

    var authResponse by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf("")
        private set

    suspend fun signup(username: String, email: String, password: String) {
        val inputUsername = username.trim()
        val inputEmail = email.trim()
        val inputPassword = password.trim()
        if (inputUsername.isBlank() || inputUsername.isBlank() ||
            !Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches() ||
            inputPassword.isBlank() || inputPassword.isEmpty() || inputPassword.length < 6
            || inputUsername.length < 6
        ) {
            return
        }
        withContext(viewModelScope.coroutineContext) {

            authenticationRepository.signUp(inputUsername, inputEmail, inputPassword).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = it.isLoading
                    }
                    is Resource.Success -> {
                        authResponse = it.data ?: false
                    }
                    is Resource.Error -> {
                        errorMessage = it.error?.message ?: "Unknown error"
                    }
                }
            }
        }
    }


}
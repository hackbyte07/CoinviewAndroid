package com.hackbyte.coinview.domain.repository

import com.hackbyte.coinview.domain.models.AuthResponse
import com.hackbyte.coinview.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    suspend fun signIn(username: String, password: String): Flow<Resource<AuthResponse>>
    suspend fun signUp(username: String, email: String, password: String): Flow<Resource<Boolean>>
    suspend fun authenticate(token: String): Flow<Resource<Boolean>>
}
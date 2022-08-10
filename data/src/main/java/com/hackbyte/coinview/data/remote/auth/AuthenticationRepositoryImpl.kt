package com.hackbyte.coinview.data.remote.auth

import android.util.Patterns
import com.hackbyte.coinview.domain.models.AuthResponse
import com.hackbyte.coinview.domain.repository.AuthenticationRepository
import com.hackbyte.coinview.domain.utils.Resource
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber

class AuthenticationRepositoryImpl(
    private val client: HttpClient
) : AuthenticationRepository {

    override suspend fun signIn(username: String, password: String): Flow<Resource<AuthResponse>> =
        flow {
            val inputUsername = username.trim()
            val inputPassword = password.trim()
            if (inputPassword.isEmpty() || inputPassword.length < 6 || inputUsername.isEmpty())
                return@flow
            try {
                emit(Resource.Loading(true))
                val data = client.post {
                    url(AuthUrlConstants.SIGN_IN)
                    contentType(ContentType.Application.Json)
                    setBody(User(username = inputUsername, password = inputPassword))
                }.body() as AuthResponseDto
                emit(Resource.Success(AuthResponse(data.token)))
            } catch (t: Throwable) {
                t.printStackTrace()
                Timber.d(t.message)
                emit(Resource.Error(t))
            } finally {
                emit(Resource.Loading(false))
            }
        }

    override suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): Flow<Resource<Boolean>> = flow {
        val inputUsername = username.trim()
        val inputEmail = email.trim()
        val inputPassword = password.trim()
        if (inputPassword.isEmpty() || inputPassword.length < 6 || inputUsername.isEmpty()
            || !Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()
        )
            return@flow
        try {
            emit(Resource.Loading(true))
            client.post {
                url(AuthUrlConstants.SIGN_UP)
                contentType(ContentType.Application.Json)
                setBody(User(username, email, password))
            }
            emit(Resource.Success(true))
        } catch (t: Throwable) {
            t.printStackTrace()
            Timber.d(t.message)
            emit(Resource.Error(t))
        } finally {
            emit(Resource.Loading(false))
        }
    }

    override suspend fun authenticate(token: String): Flow<Resource<Boolean>> {
        TODO("Not yet implemented")
    }
}
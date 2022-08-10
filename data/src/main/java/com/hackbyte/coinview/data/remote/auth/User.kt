package com.hackbyte.coinview.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val email: String = "",
    val password: String
)

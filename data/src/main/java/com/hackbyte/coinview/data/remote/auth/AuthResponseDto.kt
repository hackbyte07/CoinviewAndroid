package com.hackbyte.coinview.data.remote.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthResponseDto(
    val token: String
)

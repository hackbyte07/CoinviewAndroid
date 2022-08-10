package com.hackbyte.coinview.data.remote.auth

object AuthUrlConstants {

    private const val BASE_URL = "https://coin-view-application.herokuapp.com/api/v1/"

    const val SIGN_IN = BASE_URL.plus("signin")
    const val SIGN_UP = BASE_URL.plus("signup")
    const val AUTHENTICATE = BASE_URL.plus("authenticate")

}
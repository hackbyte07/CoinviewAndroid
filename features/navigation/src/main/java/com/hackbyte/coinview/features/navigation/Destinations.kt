package com.hackbyte.coinview.features.navigation

import com.hackbyte.coinview.features.authentication.destinations.LoginScreenDestination
import com.hackbyte.coinview.features.authentication.destinations.SignupScreenDestination
import com.hackbyte.coinview.features.feature_app_screen.destinations.AppScreenDestination
import com.hackbyte.coinview.features.feature_detail_screen.destinations.DetailScreenDestination
import com.hackbyte.coinview.features.feature_exchange_screen.destinations.ExchangeScreenDestination
import com.hackbyte.coinview.features.feature_search_screen.destinations.SearchScreenDestination
import com.hackbyte.coinview.features.fetaure_home_screen.destinations.HomeScreenDestination

object Destinations {
    val loginScreenDestination = LoginScreenDestination
    val signupScreenDestination = SignupScreenDestination
    val homeScreenDestination = HomeScreenDestination
    val searchScreenDestination = SearchScreenDestination
    val exchangeScreenDestination = ExchangeScreenDestination
    val appScreenDestination = AppScreenDestination
    val detailScreenDestination = DetailScreenDestination
}
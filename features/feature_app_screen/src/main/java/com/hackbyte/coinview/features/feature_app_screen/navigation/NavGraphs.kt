package com.hackbyte.coinview.features.feature_app_screen.navigation

import com.hackbyte.coinview.features.feature_detail_screen.destinations.DetailScreenDestination
import com.hackbyte.coinview.features.feature_exchange_screen.destinations.ExchangeScreenDestination
import com.hackbyte.coinview.features.feature_search_screen.destinations.SearchScreenDestination
import com.hackbyte.coinview.features.fetaure_home_screen.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object BottomBarNavGraph : NavGraphSpec {

    const val homeScreen = "home_screen"
    const val searchScreen = "search_screen"
    const val exchangeScreen = "exchange_screen"
    const val detailScreen = "detail_screen"
    const val bottomBarNavGraph = "bottom_bar_nav_graph"

    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = mapOf(
            Pair(homeScreen, HomeScreenDestination),
            Pair(searchScreen, SearchScreenDestination),
            Pair(exchangeScreen, ExchangeScreenDestination),
            Pair(detailScreen, DetailScreenDestination)
        )
    override val route: String
        get() = bottomBarNavGraph
    override val startRoute: Route
        get() = HomeScreenDestination
}

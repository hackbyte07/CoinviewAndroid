package com.hackbyte.coinview.features.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route

object AppNavGraph : NavGraphSpec {
    const val appScreen = "app_screen"
    const val detailScreen = "detail_screen"
    const val appNavGraph = "app_nav_graph"
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = mapOf(
            Pair(appScreen, Destinations.appScreenDestination),
            Pair(detailScreen, Destinations.detailScreenDestination)
        )
    override val route: String
        get() = appNavGraph
    override val startRoute: Route
        get() = Destinations.appScreenDestination
}
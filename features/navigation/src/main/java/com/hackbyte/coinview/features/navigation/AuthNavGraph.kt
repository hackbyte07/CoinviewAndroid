package com.hackbyte.coinview.features.navigation

import com.ramcosta.composedestinations.spec.DestinationSpec
import com.ramcosta.composedestinations.spec.NavGraphSpec
import com.ramcosta.composedestinations.spec.Route


object AuthNavGraph : NavGraphSpec {
    const val loginScreen = "login_screen"
    const val signupScreen = "signup_screen"
    const val authNavGraph = "auth_nav_graph"
    override val destinationsByRoute: Map<String, DestinationSpec<*>>
        get() = mapOf(
            Pair(loginScreen, Destinations.loginScreenDestination),
            Pair(signupScreen, Destinations.signupScreenDestination)
        )
    override val route: String
        get() = authNavGraph
    override val startRoute: Route
        get() = Destinations.loginScreenDestination
    override val nestedNavGraphs: List<NavGraphSpec>
        get() = listOf(AppNavGraph)
}
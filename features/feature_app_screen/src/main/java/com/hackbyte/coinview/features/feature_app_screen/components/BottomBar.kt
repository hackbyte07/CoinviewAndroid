package com.hackbyte.coinview.features.feature_app_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Place
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.hackbyte.coinview.features.feature_exchange_screen.destinations.ExchangeScreenDestination
import com.hackbyte.coinview.features.feature_search_screen.destinations.SearchScreenDestination
import com.hackbyte.coinview.features.fetaure_home_screen.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.currentDestinationAsState


sealed class BottomBarNavigation(val route: DirectionDestinationSpec, val icon: ImageVector) {
    object HomeScreen : BottomBarNavigation(HomeScreenDestination, Icons.Outlined.Home)
    object SearchScreen : BottomBarNavigation(SearchScreenDestination, Icons.Outlined.Search)
    object ExchangeScreen : BottomBarNavigation(ExchangeScreenDestination, Icons.Outlined.Place)
}

@Composable
fun BottomBar(navController: NavController) {

    val items = listOf(
        BottomBarNavigation.HomeScreen,
        BottomBarNavigation.SearchScreen,
        BottomBarNavigation.ExchangeScreen
    )
    val destination = navController.currentDestinationAsState().value?.route
    BottomAppBar {

        items.forEach { item ->
            this.NavigationBarItem(
                selected = destination == item.route.route,
                icon = { Icon(imageVector = item.icon, contentDescription = "icon") },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { screen_route ->
                            popUpTo(screen_route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

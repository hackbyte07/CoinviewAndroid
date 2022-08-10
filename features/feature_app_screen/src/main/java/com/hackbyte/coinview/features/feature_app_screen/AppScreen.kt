package com.hackbyte.coinview.features.feature_app_screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.hackbyte.coinview.features.common.components.ScreenTransition
import com.hackbyte.coinview.features.feature_app_screen.components.BottomBar
import com.hackbyte.coinview.features.feature_app_screen.navigation.BottomBarNavGraph
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.annotation.Destination


@OptIn(
    ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class,
    ExperimentalMaterialNavigationApi::class
)
@Destination(style = ScreenTransition::class)
@Composable
fun AppScreen() {
    val navController = rememberAnimatedNavController()
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) {

        DestinationsNavHost(
            navGraph = BottomBarNavGraph,
            startRoute = BottomBarNavGraph.startRoute,
            navController = navController,
            engine = rememberAnimatedNavHostEngine(),
            modifier = Modifier.padding(bottom = it.calculateBottomPadding())
        )
    }
}
package com.hackbyte.coinview.features.common.components

import androidx.compose.animation.*
import androidx.navigation.NavBackStackEntry
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object ScreenTransition : DestinationStyle.Animated {

    override fun AnimatedContentScope<NavBackStackEntry>.enterTransition(): EnterTransition? {
        return slideInHorizontally()
    }

    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return slideOutHorizontally()
    }

}
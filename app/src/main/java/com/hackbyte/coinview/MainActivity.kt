package com.hackbyte.coinview

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.hackbyte.coinview.features.navigation.AppNavGraph
import com.hackbyte.coinview.features.navigation.AuthNavGraph
import com.hackbyte.coinview.ui.theme.CoinviewTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {


    private val mainViewModel by inject<MainViewModel>()

    @OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition {
            mainViewModel.showSplashScreen.value
        }
        setContent {
            val engine = rememberAnimatedNavHostEngine()
            val systemUiController = rememberSystemUiController()

            CoinviewTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val backgroundColor = MaterialTheme.colorScheme.background
                    if (isSystemInDarkTheme()) {
                        SideEffect {
                            systemUiController.apply {
                                setStatusBarColor(color = backgroundColor, darkIcons = false)
                                setNavigationBarColor(color = backgroundColor, darkIcons = false)
                            }
                        }
                    } else {
                        SideEffect {
                            systemUiController.apply {
                                setStatusBarColor(color = backgroundColor, darkIcons = true)
                                setNavigationBarColor(color = backgroundColor, darkIcons = true)
                            }
                        }
                    }
                    if (mainViewModel.token.isEmpty()) {
                        DestinationsNavHost(
                            navGraph = AuthNavGraph,
                            startRoute = AuthNavGraph.startRoute,
                            engine = engine
                        )
                    } else {
                        DestinationsNavHost(
                            navGraph = AppNavGraph,
                            startRoute = AppNavGraph.startRoute,
                            engine = engine
                        )
                    }
                }
            }
        }
    }

}

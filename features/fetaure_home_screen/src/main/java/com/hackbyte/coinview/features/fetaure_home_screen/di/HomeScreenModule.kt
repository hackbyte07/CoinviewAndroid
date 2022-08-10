package com.hackbyte.coinview.features.fetaure_home_screen.di

import com.hackbyte.coinview.features.fetaure_home_screen.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val homeScreenModule = module {
    viewModelOf(::HomeViewModel)
}
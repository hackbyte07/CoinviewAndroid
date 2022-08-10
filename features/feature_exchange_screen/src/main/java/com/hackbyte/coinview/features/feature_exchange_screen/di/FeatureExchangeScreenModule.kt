package com.hackbyte.coinview.features.feature_exchange_screen.di

import com.hackbyte.coinview.features.feature_exchange_screen.ExchangeViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::ExchangeViewModel)
}
val FeatureExchangeScreenModule = loadKoinModules(listOf(appModule))


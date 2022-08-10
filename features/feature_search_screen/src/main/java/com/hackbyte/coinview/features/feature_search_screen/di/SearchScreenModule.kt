package com.hackbyte.coinview.features.feature_search_screen.di

import com.hackbyte.coinview.features.feature_search_screen.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val searchScreenModule = module {
    viewModelOf(::SearchViewModel)
}
package com.hackbyte.coinview.features.feature_detail_screen.di

import com.hackbyte.coinview.features.feature_detail_screen.DetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val detailModule = module {
    viewModelOf(::DetailViewModel)
}
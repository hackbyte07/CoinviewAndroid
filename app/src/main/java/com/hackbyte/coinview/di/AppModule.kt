package com.hackbyte.coinview.di

import com.hackbyte.coinview.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.createdAtStart
import org.koin.dsl.module


val appModule = module {
    viewModelOf(::MainViewModel) { createdAtStart() }
}
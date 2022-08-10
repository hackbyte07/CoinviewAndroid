package com.hackbyte.coinview.domain.di

import org.koin.core.context.loadKoinModules


val domainModule = loadKoinModules(
    listOf(
        coinsUseCaseModule
    )
)
package com.hackbyte.coinview

import android.app.Application
import com.hackbyte.coinview.data.di.DataModule
import com.hackbyte.coinview.di.appModule
import com.hackbyte.coinview.domain.di.domainModule
import com.hackbyte.coinview.features.authentication.di.authenticationModule
import com.hackbyte.coinview.features.common.di.commonModule
import com.hackbyte.coinview.features.feature_detail_screen.di.detailModule
import com.hackbyte.coinview.features.feature_exchange_screen.di.FeatureExchangeScreenModule
import com.hackbyte.coinview.features.feature_search_screen.di.searchScreenModule
import com.hackbyte.coinview.features.fetaure_home_screen.di.homeScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class CoinviewApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoinviewApplication)
            androidLogger(level = Level.DEBUG)
            DataModule
            domainModule
            modules(
                appModule,
                commonModule,
                homeScreenModule,
                searchScreenModule,
                detailModule,
                authenticationModule,
            )
            FeatureExchangeScreenModule
        }
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}
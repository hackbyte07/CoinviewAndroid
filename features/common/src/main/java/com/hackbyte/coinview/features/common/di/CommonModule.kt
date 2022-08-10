package com.hackbyte.coinview.features.common.di

import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.hackbyte.coinview.features.common.datastore.PrefDataStoreRepository
import com.hackbyte.coinview.features.common.network_manager.NetworkManager
import com.hackbyte.coinview.features.common.network_manager.NetworkManagerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.createdAtStart
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private const val USER_PREFERENCES = "userPreferences"


val commonModule = module {
    single {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler(
                produceNewData = { emptyPreferences() }
            ),
            migrations = listOf(
                SharedPreferencesMigration(
                    androidContext().applicationContext,
                    USER_PREFERENCES
                )
            ),
            scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
            produceFile = {
                androidContext().applicationContext.preferencesDataStoreFile(
                    USER_PREFERENCES
                )
            }
        )
    }
    singleOf(::PrefDataStoreRepository) { createdAtStart() }
    singleOf(::NetworkManagerImpl) {
        bind<NetworkManager>()
    }
}
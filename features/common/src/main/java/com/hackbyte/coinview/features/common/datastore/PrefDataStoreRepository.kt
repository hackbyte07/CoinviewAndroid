package com.hackbyte.coinview.features.common.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import timber.log.Timber
import java.io.IOException


class PrefDataStoreRepository(
    private val dataStore: DataStore<Preferences>
) {
    // TODO: save username is not working please check 
    private object PreferencesKeys {
        val currency = stringPreferencesKey("currency")
        val authToken = stringPreferencesKey("auth_token")
        val username = stringPreferencesKey("user_name")
    }

    suspend fun saveCurrencyToDataStore(currency: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.currency] = currency
        }
    }

    suspend fun saveAuthTokenToDataStore(token: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.authToken] = token
        }
    }

    suspend fun saveUsernameToDataStore(username: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.username] = username
        }
    }

    val readUsernameFromDataStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            exception.printStackTrace()
            Timber.d(exception.message + " Error")
            emit(emptyPreferences())
        } else {
            Timber.d(exception.message + " Error")
            throw exception
        }
    }.map {
        it[PreferencesKeys.username] ?: "Guest"
    }.flowOn(Dispatchers.IO)

    val readAuthTokenFromDataStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            exception.printStackTrace()
            Timber.d(exception.message + " Error")
            emit(emptyPreferences())
        } else {
            Timber.d(exception.message + " Error")
            throw exception
        }
    }.map {
        it[PreferencesKeys.authToken] ?: ""
    }.flowOn(Dispatchers.IO)

    val readCurrencyFromDataStore: Flow<String> = dataStore.data.catch { exception ->
        if (exception is IOException) {
            exception.printStackTrace()
            Timber.d(exception.message + " Error")
            emit(emptyPreferences())
        } else {
            Timber.d(exception.message + " Error...")
            throw exception
        }
    }.map {
        it[PreferencesKeys.currency] ?: "usd"
    }.flowOn(Dispatchers.IO)

}
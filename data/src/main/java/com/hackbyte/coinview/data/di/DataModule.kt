package com.hackbyte.coinview.data.di

import androidx.room.Room
import coingecko.CoinGeckoClient
import com.hackbyte.coinview.data.local.CoinsDatabase
import com.hackbyte.coinview.data.remote.auth.AuthenticationRepositoryImpl
import com.hackbyte.coinview.data.remote.coins.CoinsRepositoryImpl
import com.hackbyte.coinview.data.remote.exchange.ExchangeRepositoryImpl
import com.hackbyte.coinview.domain.repository.AuthenticationRepository
import com.hackbyte.coinview.domain.repository.CoinsRepository
import com.hackbyte.coinview.domain.repository.ExchangeRepository
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import timber.log.Timber


val appModule = module {
    singleOf<CoinGeckoClient>() {
        CoinGeckoClient()
    }

    single() {
        val db = Room.databaseBuilder(
            androidContext().applicationContext,
            CoinsDatabase::class.java,
            "coins_database"
        ).fallbackToDestructiveMigration()
            .build()
        db
    }

    singleOf<HttpClient>() {
        val client = HttpClient(Android) {
            install(HttpTimeout) {
                socketTimeoutMillis = 25_000
                connectTimeoutMillis = 25_000
                requestTimeoutMillis = 25_000
            }
            install(DefaultRequest) {
                accept(ContentType.Application.Json)
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
            install(Logging) {
                level = LogLevel.ALL
                logger = Logger.DEFAULT
                object : Logger {
                    override fun log(message: String) {
                        Timber.tag("ktor").d(message)
                    }
                }
            }
        }
        client
    }

}

val repositoryModule = module {
    singleOf(::CoinsRepositoryImpl) {
        bind<CoinsRepository>()
    }

    singleOf(::ExchangeRepositoryImpl) {
        bind<ExchangeRepository>()
    }

    singleOf(::AuthenticationRepositoryImpl) {
        bind<AuthenticationRepository>()
    }
}

val DataModule = loadKoinModules(listOf(appModule, repositoryModule))
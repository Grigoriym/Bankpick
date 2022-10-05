package com.grappim.bankpick.di

import com.grappim.bankpick.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import timber.log.Timber
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class NetworkModule {

    @[Provides Singleton]
    fun provideHttpClient(): HttpClient {
        val client = HttpClient(Android) {
            expectSuccess = true

            defaultRequest {
                host = BuildConfig.GRAPPIM_API
                url {
                    url("/api/v1/")
                    protocol = URLProtocol.HTTPS
                }
            }

            if (BuildConfig.DEBUG) {
                install(Logging) {
                    logger = object : Logger {
                        override fun log(message: String) {
                            Timber.d(message)
                        }
                    }

                    level = LogLevel.ALL
                }
            }

            install(ContentNegotiation) {
                json(Json {
                    isLenient = true
                    prettyPrint = false
                    ignoreUnknownKeys = true
                    explicitNulls = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }
        }

//        client.plugin(HttpSend).intercept { request->
//            execute(request)
//        }
        return client
    }

}
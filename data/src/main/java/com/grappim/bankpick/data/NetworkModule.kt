package com.grappim.bankpick.data

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
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

    @[Singleton Provides]
    fun provideChuckerInterceptor(
        @ApplicationContext appContext: Context
    ): ChuckerInterceptor {
        val chuckerCollector = ChuckerCollector(
            context = appContext,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        return ChuckerInterceptor.Builder(context = appContext)
            .collector(collector = chuckerCollector)
            .redactHeaders(emptySet())
            .alwaysReadResponseBody(true)
            .build()
    }

    @[Provides Singleton]
    fun provideJson(): Json = Json {
        isLenient = true
        prettyPrint = false
        ignoreUnknownKeys = true
        explicitNulls = true
    }

    @[Provides Singleton]
    fun provideHttpClient(
        json: Json,
        chuckerInterceptor: ChuckerInterceptor,
        @ApplicationContext context: Context
    ): HttpClient {
        val client = HttpClient(OkHttp) {
            expectSuccess = true

            install(NetworkConnectivityInterceptor) {
                this.context = context
            }

            defaultRequest {
                host = BuildConfig.GRAPPIM_API
                url {
                    url("/api/v1/")
                    protocol = URLProtocol.HTTP
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
                json(json)
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            engine {
                if (BuildConfig.DEBUG) {
                    addInterceptor(chuckerInterceptor)
                }
            }
        }
        return client
    }

}
package com.grappim.bankpick.compose.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.grappim.bankpick.compose.domain.model.exception.NetworkException
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.serialization.*
import io.ktor.util.*

class NetworkConnectivityInterceptor(
    val context: Context
) {

    private fun hasInternetConnection(): Boolean {
        var result = false
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
        cm?.run {
            cm.getNetworkCapabilities(cm.activeNetwork)?.run {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> true
                    else -> false
                }
            }
        }
        return result
    }

    object Configuration {
        var context: Context? = null
    }

    companion object : HttpClientPlugin<Configuration, NetworkConnectivityInterceptor> {
        override val key: AttributeKey<NetworkConnectivityInterceptor> =
            AttributeKey("NetworkConnectivityInterceptor")

        override fun install(plugin: NetworkConnectivityInterceptor, scope: HttpClient) {
            scope.requestPipeline.intercept(HttpRequestPipeline.Before) {
                if (plugin.hasInternetConnection().not()) {
                    throw NetworkException(NetworkException.ERROR_NO_INTERNET)
                }
                proceedWith(subject)
            }
        }

        override fun prepare(block: Configuration.() -> Unit): NetworkConnectivityInterceptor {
            val config = Configuration.apply(block)
            return NetworkConnectivityInterceptor(
                requireNotNull(config.context)
            )
        }
    }
}

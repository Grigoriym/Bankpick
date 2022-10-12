package com.grappim.bankpick.data

import com.grappim.bankpick.data.model.auth.base.BaseApiErrorDTO
import com.grappim.bankpick.domain.model.base.BaseApiError
import com.grappim.bankpick.domain.model.base.NetworkException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorMappingInterceptor @Inject constructor(
    private val networkConnectivityHandler: NetworkConnectivityHandler,
    private val json: Json
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response =
        try {
            if (!networkConnectivityHandler.isConnected) throw NetworkException(NetworkException.ERROR_NO_INTERNET)
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.isSuccessful) {
                response
            } else {
                throw mapErrorBodyToException(response)
            }

        } catch (throwable: Throwable) {
            Timber.e(throwable)
            throw throwable.mapNetworkException()
        }

    private fun mapErrorBodyToException(response: Response): Throwable {
        val responseBody = requireNotNull(response.body?.string())
        val dto = json.decodeFromString<BaseApiErrorDTO>(responseBody)
        val baseApiError = BaseApiError(
            system = dto.system,
            status = dto.status,
            statusCode = dto.statusCode,
            message = dto.message,
            developerMessage = dto.developerMessage
        )
        return NetworkException(
            errorCode = NetworkException.ERROR_API,
            apiError = baseApiError,
            request = "[Request] ${response.request.method} ${response.request.url}"
        )
    }

    private fun Throwable.mapNetworkException(): Throwable =
        when (this) {
            is NetworkException -> this
            is SocketTimeoutException -> NetworkException(NetworkException.ERROR_TIMEOUT, this)
            is UnknownHostException -> NetworkException(NetworkException.ERROR_HOST_NOT_FOUND, this)
            is IOException -> NetworkException(NetworkException.ERROR_NETWORK_IO, this)
            else -> NetworkException(NetworkException.ERROR_UNDEFINED, this)
        }

}
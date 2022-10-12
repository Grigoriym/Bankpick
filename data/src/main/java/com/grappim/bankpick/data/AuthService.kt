package com.grappim.bankpick.data

import com.grappim.bankpick.data.lce.runOperationCatching
import com.grappim.bankpick.data.model.auth.SignInDataDTO
import com.grappim.bankpick.data.model.auth.SignUpDataDTO
import com.grappim.bankpick.domain.lce.Try
import com.grappim.bankpick.domain.model.auth.SignInData
import com.grappim.bankpick.domain.model.auth.SignUpData
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthService @Inject constructor(
    private val httpClient: HttpClient
) {

    suspend fun signIn(
        signInData: SignInData
    ): Try<Unit, Throwable> = runOperationCatching {
        httpClient.post("signin") {
            contentType(ContentType.Application.Json)
            setBody(
                SignInDataDTO(
                    email = signInData.email,
                    password = signInData.password
                )
            )
        }
    }

    suspend fun signUp(
        signUpData: SignUpData
    ): Try<Unit, Throwable> = runOperationCatching {
        httpClient.post("signup") {
            contentType(ContentType.Application.Json)
            setBody(
                SignUpDataDTO(
                    email = signUpData.email,
                    password = signUpData.password,
                    fullName = signUpData.fullName,
                    phoneNumber = signUpData.phoneNumber
                )
            )
        }
    }
}
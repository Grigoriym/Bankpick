package com.grappim.bankpick.data

import com.grappim.bankpick.data.model.SignInDataDTO
import com.grappim.bankpick.data.model.SignUpDataDTO
import com.grappim.bankpick.domain.auth.SignInData
import com.grappim.bankpick.domain.auth.SignUpData
import com.grappim.bankpick.domain.service.AuthService
import com.grappim.bankpick.lce.Try
import com.grappim.bankpick.lce.runOperationCatching
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthServiceImpl @Inject constructor(
    private val httpClient: HttpClient
) : AuthService {

    override suspend fun signIn(
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

    override suspend fun signUp(
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
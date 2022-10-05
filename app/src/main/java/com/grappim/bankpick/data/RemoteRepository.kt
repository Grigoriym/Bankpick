package com.grappim.bankpick.data

import com.grappim.bankpick.domain.auth.SignInData
import com.grappim.bankpick.domain.auth.SignUpData
import com.grappim.bankpick.domain.service.AuthService
import com.grappim.bankpick.lce.Try
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteRepository @Inject constructor(
    private val authService: AuthService
) {

    suspend fun signIn(
        signInData: SignInData
    ): Try<Unit, Throwable> = authService.signIn(signInData)

    suspend fun signUp(
        signUpData: SignUpData
    ): Try<Unit, Throwable> = authService.signUp(signUpData)
}
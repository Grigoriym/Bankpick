package com.grappim.bankpick.compose.domain.service

import com.grappim.bankpick.compose.domain.auth.SignInData
import com.grappim.bankpick.compose.domain.auth.SignUpData
import com.grappim.bankpick.compose.lce.Try

interface AuthService {

    suspend fun signIn(signInData: SignInData): Try<Unit, Throwable>
    suspend fun signUp(signUpData: SignUpData): Try<Unit, Throwable>
}
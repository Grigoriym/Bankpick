package com.grappim.bankpick.domain.service

import com.grappim.bankpick.domain.auth.SignInData
import com.grappim.bankpick.domain.auth.SignUpData
import com.grappim.bankpick.lce.Try

interface AuthService {

    suspend fun signIn(signInData: SignInData): Try<Unit, Throwable>
    suspend fun signUp(signUpData: SignUpData): Try<Unit, Throwable>
}
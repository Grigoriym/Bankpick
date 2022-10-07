package com.grappim.bankpick.compose.di

import com.grappim.bankpick.compose.data.AuthServiceImpl
import com.grappim.bankpick.compose.domain.service.AuthService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@[Module InstallIn(SingletonComponent::class)]
interface ApiModule {

    @Binds
    fun bindAuthService(
        authServiceImpl: AuthServiceImpl
    ): AuthService
}
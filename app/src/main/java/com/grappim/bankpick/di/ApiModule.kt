package com.grappim.bankpick.di

import com.grappim.bankpick.data.AuthServiceImpl
import com.grappim.bankpick.domain.service.AuthService
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
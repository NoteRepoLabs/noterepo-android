/**
 * 2025 - NoteRepo Engineering, Source Code for NoteRepo's Android App
 *
 *                     GNU GENERAL PUBLIC LICENSE
 *                        Version 3, 29 June 2007
 *
 *  Copyright (C) 2007 Free Software Foundation, Inc. <https://fsf.org/>
 *  Everyone is permitted to copy and distribute verbatim copies
 *  of this license document, but changing it is not allowed.
 *
 */


package dev.noterepo.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.noterepo.app.data.mappers.SignUpMapper
import dev.noterepo.app.data.remote.ApiService
import dev.noterepo.app.data.repositories.AuthRepositoryImpl
import dev.noterepo.app.domain.repositories.AuthRepository
import dev.noterepo.app.domain.usecases.AuthUseCase

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    // Provide Auth Repository
    @Provides
    fun provideAuthRepository(apiService: ApiService, signUpMapper: SignUpMapper): AuthRepository {
        return AuthRepositoryImpl(apiService, signUpMapper)
    }

    // Provide Auth UseCase
    @Provides
    fun provideSignUpUseCase(authRepository: AuthRepository): AuthUseCase {
        return AuthUseCase(authRepository)
    }
}
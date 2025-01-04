package dev.noterepo.app.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.noterepo.app.data.mappers.SignInMapper
import dev.noterepo.app.data.mappers.SignUpMapper
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    @Singleton
    fun provideSignUpMapper(): SignUpMapper {
        return SignUpMapper()
    }

    @Provides
    @Singleton
    fun provideSignInMapper(): SignInMapper {
        return SignInMapper()
    }
}
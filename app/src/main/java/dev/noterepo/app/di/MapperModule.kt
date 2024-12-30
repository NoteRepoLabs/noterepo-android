package dev.noterepo.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.noterepo.app.data.mappers.SignUpMapper

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun provideSignUpMapper(): SignUpMapper {
        return SignUpMapper()
    }
}
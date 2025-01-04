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


package dev.noterepo.app.core.di

import android.content.Context
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.noterepo.app.data.local.TokenManager
import javax.inject.Singleton

object TaskManagerModule {

    @Provides
    @Singleton
    fun provideTokenManager(@ApplicationContext context: Context): TokenManager {
        return TokenManager(context)
    }

}
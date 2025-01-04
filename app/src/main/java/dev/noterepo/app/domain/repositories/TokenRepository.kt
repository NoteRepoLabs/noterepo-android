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

package dev.noterepo.app.domain.repositories

import kotlinx.coroutines.flow.Flow

interface TokenRepository {

    fun getAccessToken(): Flow<String?>

    fun getRefreshToken(): Flow<String?>

    fun getUserId(): Flow<String?>

    fun getLastLoginMillis(): Flow<Long?>

    suspend fun saveTokens(accessToken: String, refreshToken: String, userId: String)

    suspend fun clearTokens()

}
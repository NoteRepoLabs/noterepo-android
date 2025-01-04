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

package dev.noterepo.app.data.repositories

import dev.noterepo.app.data.local.preferences.TokenManager
import dev.noterepo.app.domain.repositories.TokenRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val tokenManager: TokenManager
) : TokenRepository {
    override fun getAccessToken(): Flow<String?> {
        return tokenManager.accessToken
    }

    override fun getRefreshToken(): Flow<String?> {
        return tokenManager.refreshToken
    }

    override fun getUserId(): Flow<String?> {
        return tokenManager.userId
    }

    override fun getLastLoginMillis(): Flow<Long?> {
        return tokenManager.lastLogin
    }

    override suspend fun saveTokens(accessToken: String, refreshToken: String, userId: String) {
        return tokenManager.saveTokens(accessToken, refreshToken, userId)
    }

    override suspend fun clearTokens() {
        return tokenManager.clearTokens()
    }


}
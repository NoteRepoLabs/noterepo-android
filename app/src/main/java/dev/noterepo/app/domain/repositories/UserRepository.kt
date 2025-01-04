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

import dev.noterepo.app.data.local.entities.UserEntity
import dev.noterepo.app.domain.models.SignInResponse
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun saveUser(response: SignInResponse)
    fun getUserWithId(userId: String): Flow<UserEntity?>
    suspend fun clearUsers()
    suspend fun clearUserWithId(userId: String)
}
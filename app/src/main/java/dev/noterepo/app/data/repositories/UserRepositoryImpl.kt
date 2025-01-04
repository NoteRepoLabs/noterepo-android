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

import dev.noterepo.app.data.local.dao.UserDao
import dev.noterepo.app.data.local.entities.UserEntity
import dev.noterepo.app.domain.models.SignInResponse
import dev.noterepo.app.domain.repositories.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {
    override suspend fun saveUser(response: SignInResponse) {
        val userEntity = UserEntity(
            id = response.id,
            username = response.username,
            email = response.email,
            bio = response.bio,
            isVerified = response.isVerified,
            role = response.role
        )

        userDao.insertUser(userEntity)
    }

    override fun getUserWithId(userId: String): Flow<UserEntity?> {
        return userDao.getUserById(userId)
    }

    override suspend fun clearUserWithId(userId: String) {
        return userDao.deleteUserWithId(userId)
    }

    override suspend fun clearUsers() {
        return userDao.deleteAllUsers()
    }
}
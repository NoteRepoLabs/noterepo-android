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

package dev.noterepo.app.common.models

enum class UserRole(val type: String) {
    USER("USER"),
    ADMIN("ADMIN");

    companion object {
        fun fromString(type: String): UserRole {
            return entries.find { it.type == type }
                ?: throw IllegalArgumentException("Unknown UserRole type: $type")
        }
    }
}
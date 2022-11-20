package cz.stepesove.simplechatapp.data.remote.repositories

import cz.stepesove.simplechatapp.data.remote.AuthResult
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse

interface AuthRepository {
    suspend fun signUp(username: String, password: String): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
}
package cz.stepesove.simplechatapp.data.remote.repositories

import cz.stepesove.simplechatapp.data.remote.RequestResult

interface AuthRepository {
    suspend fun signUp(username: String, password: String): RequestResult<Unit>
    suspend fun signIn(username: String, password: String): RequestResult<Unit>
}
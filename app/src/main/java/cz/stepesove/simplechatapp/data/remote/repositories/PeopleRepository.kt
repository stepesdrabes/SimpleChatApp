package cz.stepesove.simplechatapp.data.remote.repositories

import cz.stepesove.simplechatapp.data.remote.AuthResult
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse

interface PeopleRepository {
    suspend fun getPeople(): AuthResult<List<UserResponse>>
    suspend fun currentUser(): AuthResult<UserResponse>
}
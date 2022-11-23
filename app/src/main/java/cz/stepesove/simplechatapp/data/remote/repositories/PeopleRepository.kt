package cz.stepesove.simplechatapp.data.remote.repositories

import cz.stepesove.simplechatapp.data.local.models.user.UpdateUserModel
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse

interface PeopleRepository {
    suspend fun getAllPeople(): RequestResult<List<UserResponse>>
    suspend fun currentUser(): RequestResult<UserResponse>
    suspend fun updateCurrentUser(model: UpdateUserModel): RequestResult<UserResponse>
}
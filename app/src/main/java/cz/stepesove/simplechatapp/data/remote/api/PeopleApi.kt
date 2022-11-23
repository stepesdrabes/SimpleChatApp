package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.local.models.user.UpdateUserModel
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PeopleApi {

    @GET(HttpRoutes.CURRENT_USER_URL)
    suspend fun currentUser(
        @Header("Authorization") token: String
    ): UserResponse

    @POST(HttpRoutes.CURRENT_USER_URL)
    suspend fun updateCurrentUser(
        @Header("Authorization") token: String,
        @Body model: UpdateUserModel
    ): UserResponse

    @GET(HttpRoutes.PEOPLE_URL)
    suspend fun getAllPeople(
        @Header("Authorization") token: String
    ): List<UserResponse>
}
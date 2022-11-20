package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface PeopleApi {

    @GET(HttpRoutes.CURRENT_USER_URL)
    suspend fun currentUser(
        @Header("Authorization") token: String
    ): UserResponse

    @GET(HttpRoutes.PEOPLE_URL)
    suspend fun getAllPeople(
        @Header("Authorization") token: String
    ): List<UserResponse>
}
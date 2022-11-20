package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.local.models.auth.LoginModel
import cz.stepesove.simplechatapp.data.local.models.auth.RegisterModel
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.TokenResponse
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthApi {

    @POST(HttpRoutes.AUTH_SIGNUP_URL)
    suspend fun signUp(
        @Body request: RegisterModel
    )

    @POST(HttpRoutes.AUTH_SIGNIN_URL)
    suspend fun signIn(
        @Body request: LoginModel
    ): TokenResponse
}
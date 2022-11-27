package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.local.models.auth.LoginModel
import cz.stepesove.simplechatapp.data.local.models.auth.RegisterModel
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.auth.TokenResponse
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthApi {

    @Multipart
    @POST(HttpRoutes.AUTH_SIGNUP_URL)
    suspend fun signUp(
        @Part("username") username: String,
        @Part("password") password: String,
        @Part image: MultipartBody.Part?
    )

    @POST(HttpRoutes.AUTH_SIGNIN_URL)
    suspend fun signIn(
        @Body request: LoginModel
    ): TokenResponse
}
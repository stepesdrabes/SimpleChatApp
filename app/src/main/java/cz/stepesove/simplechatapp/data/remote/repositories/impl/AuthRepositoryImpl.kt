package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import coil.network.HttpException
import cz.stepesove.simplechatapp.data.local.models.auth.LoginModel
import cz.stepesove.simplechatapp.data.local.models.auth.RegisterModel
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.api.AuthApi
import cz.stepesove.simplechatapp.data.remote.repositories.AuthRepository

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {

    override suspend fun signUp(username: String, password: String): RequestResult<Unit> {
        return try {
            api.signUp(
                username = username,
                password = password,
                image = null
            )
            signIn(username, password)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.NotFound()
        } catch (e: Exception) {
            RequestResult.NotFound()
        }
    }

    override suspend fun signIn(username: String, password: String): RequestResult<Unit> {
        return try {
            val response = api.signIn(
                request = LoginModel(
                    username = username,
                    password = password
                )
            )
            sharedPreferences.edit().putString("jwt", response.accessToken).apply()
            RequestResult.Ok()
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.NotFound()
        } catch (e: Exception) {
            RequestResult.NotFound()
        }
    }
}
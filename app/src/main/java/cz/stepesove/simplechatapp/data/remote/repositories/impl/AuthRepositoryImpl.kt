package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import coil.network.HttpException
import cz.stepesove.simplechatapp.data.local.models.auth.LoginModel
import cz.stepesove.simplechatapp.data.local.models.auth.RegisterModel
import cz.stepesove.simplechatapp.data.remote.AuthResult
import cz.stepesove.simplechatapp.data.remote.api.AuthApi
import cz.stepesove.simplechatapp.data.remote.repositories.AuthRepository
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {

    override suspend fun signUp(username: String, password: String): AuthResult<Unit> {
        return try {
            api.signUp(
                request = RegisterModel(
                    username = username,
                    password = password
                )
            )
            signIn(username, password)
        } catch (e: HttpException) {
            if (e.response.code == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        return try {
            val response = api.signIn(
                request = LoginModel(
                    username = username,
                    password = password
                )
            )
            prefs.edit()
                .putString("jwt", response.token)
                .apply()
            AuthResult.Authorized()
        } catch (e: HttpException) {
            if (e.response.code == 401) {
                AuthResult.Unauthorized()
            } else {
                AuthResult.UnknownError()
            }
        } catch (e: Exception) {
            AuthResult.UnknownError()
        }
    }
}
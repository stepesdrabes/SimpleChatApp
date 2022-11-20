package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import coil.network.HttpException
import cz.stepesove.simplechatapp.data.remote.AuthResult
import cz.stepesove.simplechatapp.data.remote.api.PeopleApi
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.UserResponse

class PeopleRepositoryImpl(
    private val peopleApi: PeopleApi,
    private val sharedPreferences: SharedPreferences
) : PeopleRepository {

    override suspend fun getPeople(): AuthResult<List<UserResponse>> {
        return try {
            val token = sharedPreferences.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val people = peopleApi.getAllPeople("Bearer $token")
            AuthResult.Authorized(people)
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

    override suspend fun currentUser(): AuthResult<UserResponse> {
        return try {
            val token = sharedPreferences.getString("jwt", null) ?: return AuthResult.Unauthorized()
            val user = peopleApi.currentUser("Bearer $token")
            AuthResult.Authorized(user)
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
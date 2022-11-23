package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import coil.network.HttpException
import cz.stepesove.simplechatapp.data.local.models.user.UpdateUserModel
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.api.PeopleApi
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse

class PeopleRepositoryImpl(
    private val peopleApi: PeopleApi,
    private val sharedPreferences: SharedPreferences
) : PeopleRepository {

    override suspend fun getAllPeople(): RequestResult<List<UserResponse>> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val people = peopleApi.getAllPeople("Bearer $token")
            RequestResult.Ok(people)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun currentUser(): RequestResult<UserResponse> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val user = peopleApi.currentUser("Bearer $token")
            RequestResult.Ok(user)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun updateCurrentUser(model: UpdateUserModel): RequestResult<UserResponse> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val user = peopleApi.updateCurrentUser("Bearer $token", model)
            RequestResult.Ok(user)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }
}
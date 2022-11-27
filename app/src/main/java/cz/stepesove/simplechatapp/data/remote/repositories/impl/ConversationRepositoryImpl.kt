package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import coil.network.HttpException
import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationMessageModel
import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationModel
import cz.stepesove.simplechatapp.data.local.models.conversations.UpdateConversationModel
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.api.ConversationApi
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationMessageResponse
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class ConversationRepositoryImpl(
    private val conversationApi: ConversationApi,
    private val sharedPreferences: SharedPreferences
) : ConversationRepository {

    override suspend fun getAllConversations(): RequestResult<List<ConversationResponse>> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val conversations = conversationApi.getAllConversations("Bearer $token")
            RequestResult.Ok(conversations)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun createConversation(model: CreateConversationModel): RequestResult<ConversationResponse> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()

            val conversations = conversationApi.createConversation(
                token = "Bearer $token",
                name = model.name.toRequestBody("multipart/form-data".toMediaTypeOrNull()),
                imageFile = model.imageFile,
                users = model.users
            )

            RequestResult.Ok(conversations)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun updateConversation(
        id: String,
        model: UpdateConversationModel
    ): RequestResult<ConversationResponse> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val conversation = conversationApi.updateConversation("Bearer $token", id, model)
            RequestResult.Ok(conversation)
        } catch (e: HttpException) {
            when (e.response.code) {
                401 -> RequestResult.Unauthorized()
                404 -> RequestResult.NotFound()
                else -> RequestResult.UnknownError()
            }
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun deleteConversation(id: String): RequestResult<Unit> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            conversationApi.deleteConversation("Bearer $token", id)
            RequestResult.Ok()
        } catch (e: HttpException) {
            when (e.response.code) {
                401 -> RequestResult.Unauthorized()
                404 -> RequestResult.NotFound()
                else -> RequestResult.UnknownError()
            }
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun getConversationMessages(id: String): RequestResult<List<ConversationMessageResponse>> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val messages = conversationApi.getConversationMessages("Bearer $token", id)
            RequestResult.Ok(messages)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun createConversationMessage(
        conversationId: String,
        model: CreateConversationMessageModel
    ): RequestResult<ConversationMessageResponse> {
        return try {
            val token =
                sharedPreferences.getString("jwt", null) ?: return RequestResult.Unauthorized()
            val message =
                conversationApi.createConversationMessage(token = "Bearer $token", model = model)
            RequestResult.Ok(message)
        } catch (e: HttpException) {
            if (e.response.code == 401) RequestResult.Unauthorized()
            else RequestResult.UnknownError()
        } catch (e: Exception) {
            RequestResult.UnknownError()
        }
    }

    override suspend fun deleteConversationMessage(
        conversationId: String,
        id: String
    ): RequestResult<Unit> {
        TODO("Not yet implemented")
    }
}
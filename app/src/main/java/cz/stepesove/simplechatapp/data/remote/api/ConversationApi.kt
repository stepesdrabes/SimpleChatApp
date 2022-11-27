package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationMessageModel
import cz.stepesove.simplechatapp.data.local.models.conversations.UpdateConversationModel
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationMessageResponse
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ConversationApi {

    @GET(HttpRoutes.CONVERSATIONS_URL)
    suspend fun getAllConversations(
        @Header("Authorization") token: String
    ): List<ConversationResponse>

    @Multipart
    @POST(HttpRoutes.CONVERSATIONS_URL)
    suspend fun createConversation(
        @Header("Authorization") token: String,
        @Part("name") name: RequestBody,
        @Part imageFile: MultipartBody.Part?,
        @Part("users") users: List<String>
    ): ConversationResponse

    @DELETE(HttpRoutes.CONVERSATION_URL + "/{id}")
    suspend fun deleteConversation(
        @Header("Authorization") token: String,
        @Path("id") id: String
    )

    @POST(HttpRoutes.CONVERSATION_URL + "/{id}")
    suspend fun updateConversation(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body model: UpdateConversationModel
    ): ConversationResponse

    @GET(HttpRoutes.MESSAGES_URL + "/{id}")
    suspend fun getConversationMessages(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): List<ConversationMessageResponse>

    @POST(HttpRoutes.MESSAGES_URL + "/{id}")
    suspend fun createConversationMessage(
        @Header("Authorization") token: String,
        @Body model: CreateConversationMessageModel
    ): ConversationMessageResponse
}
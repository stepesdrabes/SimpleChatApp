package cz.stepesove.simplechatapp.data.remote.api

import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationModel
import cz.stepesove.simplechatapp.data.local.models.conversations.UpdateConversationModel
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface ConversationApi {

    @GET(HttpRoutes.CONVERSATIONS_URL)
    suspend fun getAllConversations(
        @Header("Authorization") token: String
    ): List<ConversationResponse>

    @POST(HttpRoutes.CONVERSATIONS_URL)
    suspend fun createConversation(
        @Header("Authorization") token: String,
        @Body model: CreateConversationModel
    ): ConversationResponse

    @DELETE(HttpRoutes.CONVERSATION_URL + "/{id}")
    suspend fun deleteConversation(
        @Header("Authorization") token: String,
        @Path("id") id: String
    )

    @POST(HttpRoutes.CONVERSATION_URL + "/{id}")
    suspend fun updateConversation(
        @Header("Authorization") token: String,
        @Body model: UpdateConversationModel
    ): ConversationResponse
}
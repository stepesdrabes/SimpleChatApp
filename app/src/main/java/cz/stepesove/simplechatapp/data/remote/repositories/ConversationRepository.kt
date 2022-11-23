package cz.stepesove.simplechatapp.data.remote.repositories

import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationMessageModel
import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationModel
import cz.stepesove.simplechatapp.data.local.models.conversations.UpdateConversationModel
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationMessageResponse
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse

interface ConversationRepository {

    // Conversations
    suspend fun getAllConversations(): RequestResult<List<ConversationResponse>>
    suspend fun createConversation(model: CreateConversationModel): RequestResult<ConversationResponse>

    suspend fun updateConversation(
        id: String,
        model: UpdateConversationModel
    ): RequestResult<ConversationResponse>

    suspend fun deleteConversation(id: String): RequestResult<Unit>

    // Messages
    suspend fun getConversationMessages(id: String): RequestResult<List<ConversationMessageResponse>>

    suspend fun createConversationMessage(
        conversationId: String,
        model: CreateConversationMessageModel
    ): RequestResult<ConversationMessageResponse>

    suspend fun deleteConversationMessage(conversationId: String, id: String): RequestResult<Unit>
}
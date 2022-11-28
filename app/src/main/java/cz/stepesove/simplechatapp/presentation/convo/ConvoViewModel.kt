package cz.stepesove.simplechatapp.presentation.convo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationMessageModel
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationMessageResponse
import cz.stepesove.simplechatapp.data.remote.signalr.OnlineHubManager
import kotlinx.coroutines.launch

class ConvoViewModel(
    private val conversationRepository: ConversationRepository,
    val onlineHubManager: OnlineHubManager
) : ViewModel() {

    var newMessage by mutableStateOf("")
    var newMessageSending by mutableStateOf(false)

    var messagesLoading by mutableStateOf(false)
    var messages = mutableStateListOf<ConversationMessageResponse>()

    fun loadMessages(conversationId: String) {
        viewModelScope.launch {
            messagesLoading = true

            val result = conversationRepository.getConversationMessages(conversationId)
            messages.clear()
            result.data?.let { messages.addAll(it) }

            messagesLoading = false
        }
    }

    fun createMessage(conversationId: String) {
        val model = CreateConversationMessageModel(
            textContent = newMessage
        )

        viewModelScope.launch {
            newMessage = ""
            newMessageSending = true
            val result = conversationRepository.createConversationMessage(conversationId, model)

            result.data?.let { messages.add(0, it) }
            newMessageSending = false
        }
    }
}
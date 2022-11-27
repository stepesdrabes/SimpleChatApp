package cz.stepesove.simplechatapp.presentation.convo

import androidx.lifecycle.ViewModel
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.signalr.OnlineHubManager

class ConvoViewModel(
    private val conversationRepository: ConversationRepository,
    val onlineHubManager: OnlineHubManager
) : ViewModel() {

    fun loadMessages() {
        
    }
}
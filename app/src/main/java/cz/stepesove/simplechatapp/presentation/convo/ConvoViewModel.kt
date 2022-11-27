package cz.stepesove.simplechatapp.presentation.convo

import androidx.lifecycle.ViewModel
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository

class ConvoViewModel(
    private val conversationRepository: ConversationRepository
) : ViewModel() {

    fun loadMessages() {
        
    }
}
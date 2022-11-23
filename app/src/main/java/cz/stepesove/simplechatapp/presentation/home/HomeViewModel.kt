package cz.stepesove.simplechatapp.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.microsoft.signalr.HubConnectionBuilder
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import kotlinx.coroutines.launch

class HomeViewModel(
    private val conversationRepository: ConversationRepository,
    private val peopleRepository: PeopleRepository,
) : ViewModel() {

    var conversationsLoading by mutableStateOf(false)
    var peopleLoading by mutableStateOf(false)

    var conversationsState by mutableStateOf<List<ConversationResponse>>(emptyList())
    var peopleState by mutableStateOf<List<UserResponse>>(emptyList())

    init {
        loadConversations()
        loadPeople()
    }

    fun loadConversations() {
        viewModelScope.launch {
            conversationsLoading = true
            val result = conversationRepository.getAllConversations()
            conversationsState = result.data ?: emptyList()
            conversationsLoading = false
        }
    }

    fun loadPeople() {
        viewModelScope.launch {
            peopleLoading = true
            val result = peopleRepository.getAllPeople()
            peopleState = result.data ?: emptyList()
            peopleLoading = false
        }
    }

    private fun connectToSocket() {
        val hub = HubConnectionBuilder
            .create(HttpRoutes.ONLINE_HUB_URL)
            .withHeader("Authorization", "")
            .withHandshakeResponseTimeout(5000)
            .build()
    }
}
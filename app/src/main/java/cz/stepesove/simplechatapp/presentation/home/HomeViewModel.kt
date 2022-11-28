package cz.stepesove.simplechatapp.presentation.home

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.data.remote.signalr.OnlineHubManager
import kotlinx.coroutines.launch

class HomeViewModel(
    private val conversationRepository: ConversationRepository,
    private val peopleRepository: PeopleRepository,
    private val sharedPreferences: SharedPreferences,
    val onlineHubManager: OnlineHubManager
) : ViewModel() {

    var peopleLoading by mutableStateOf(false)
    var conversationsLoading by mutableStateOf(false)
    var currentUserLoading by mutableStateOf(false)

    var peopleState by mutableStateOf<List<UserResponse>>(emptyList())
    var conversationsState by mutableStateOf<List<ConversationResponse>>(emptyList())
    var currentUserState by mutableStateOf<UserResponse?>(null)

    var optionStaySignedIn by mutableStateOf(false)
    var optionsAppearAsOnline by mutableStateOf(false)

    init {
        currentUserLoading = true
        optionStaySignedIn = sharedPreferences.getBoolean("options.staySignedIn", true)
        optionsAppearAsOnline = sharedPreferences.getBoolean("options.appearAsOnline", true)

        loadCurrentUser()
        loadConversations()
        loadPeople()
    }

    private fun loadCurrentUser() {
        viewModelScope.launch {
            currentUserLoading = true

            val result = peopleRepository.currentUser()
            currentUserState = result.data
            if (currentUserState != null) onlineHubManager.connect(optionsAppearAsOnline)

            currentUserLoading = false
        }
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

    fun toggleOptionStaySignedIn() {
        optionStaySignedIn = !optionStaySignedIn
        sharedPreferences.edit().putBoolean("options.staySignedIn", optionStaySignedIn).apply()
    }

    fun toggleOptionAppearAsOnline() {
        optionsAppearAsOnline = !optionsAppearAsOnline
        sharedPreferences.edit().putBoolean("options.appearAsOnline", optionsAppearAsOnline).apply()
    }

    fun signOut(onSignOut: () -> Unit) {
        sharedPreferences.edit().remove("jwt").apply()
        onSignOut()
    }
}
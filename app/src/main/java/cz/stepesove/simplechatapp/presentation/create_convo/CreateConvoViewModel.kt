package cz.stepesove.simplechatapp.presentation.create_convo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.data.local.models.conversations.CreateConversationModel
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.data.remote.signalr.OnlineHubManager
import cz.stepesove.simplechatapp.presentation.destinations.ConvoScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.CreateConvoScreenDestination
import kotlinx.coroutines.launch

class CreateConvoViewModel(
    private val peopleRepository: PeopleRepository,
    private val conversationRepository: ConversationRepository,
    val onlineHubManager: OnlineHubManager
) : ViewModel() {

    var peopleLoading by mutableStateOf(false)
    var selectedUsers = mutableStateListOf<UserResponse>()
    var convoName by mutableStateOf(TextFieldValue())

    var peopleState by mutableStateOf<List<UserResponse>>(emptyList())

    init {
        loadPeople()
    }

    private fun loadPeople() {
        viewModelScope.launch {
            peopleLoading = true
            val result = peopleRepository.getAllPeople()
            println(result)
            peopleState = result.data ?: emptyList()
            peopleLoading = false
        }
    }

    fun createConversation(currentUser: UserResponse, navigator: DestinationsNavigator) {
        val model = CreateConversationModel(
            name = convoName.text,
            imageFile = null,
            users = selectedUsers.map { it.id }
        )

        viewModelScope.launch {
            val result = conversationRepository.createConversation(model)
            if (result.data == null) return@launch

            navigator.navigate(
                ConvoScreenDestination(
                    currentUser = currentUser,
                    conversation = result.data
                )
            ) {
                popUpTo(CreateConvoScreenDestination.route) {
                    inclusive = true
                }
            }
        }
    }
}
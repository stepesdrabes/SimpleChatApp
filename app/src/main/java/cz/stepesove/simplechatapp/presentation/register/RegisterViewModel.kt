package cz.stepesove.simplechatapp.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepesove.simplechatapp.data.remote.AuthResult
import cz.stepesove.simplechatapp.data.remote.repositories.AuthRepository
import cz.stepesove.simplechatapp.presentation.login.LoginUiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(RegisterState())

    private val resultChannel = Channel<AuthResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    fun onEvent(event: RegisterUiEvent) {
        when (event) {
            is RegisterUiEvent.RegisterUsernameChanged -> state =
                state.copy(registerUsername = event.value)
            is RegisterUiEvent.RegisterPasswordChanged -> state =
                state.copy(registerPassword = event.value)
            is RegisterUiEvent.Register -> signUp()
        }
    }

    private fun signUp() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = authRepository.signUp(
                username = state.registerUsername,
                password = state.registerPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}
package cz.stepesove.simplechatapp.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.repositories.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val authRepository: AuthRepository
) : ViewModel() {

    var state by mutableStateOf(LoginState())

    private val resultChannel = Channel<RequestResult<Unit>>()
    val authResults = resultChannel.receiveAsFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.LoginUsernameChanged -> state = state.copy(loginUsername = event.value)
            is LoginUiEvent.LoginPasswordChanged -> state = state.copy(loginPassword = event.value)
            is LoginUiEvent.Login -> signIn()
        }
    }

    private fun signIn() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            val result = authRepository.signIn(
                username = state.loginUsername,
                password = state.loginPassword
            )
            resultChannel.send(result)
            state = state.copy(isLoading = false)
        }
    }
}
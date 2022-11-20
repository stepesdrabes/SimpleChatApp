package cz.stepesove.simplechatapp.presentation.login

sealed class LoginUiEvent {
    data class LoginUsernameChanged(val value: String) : LoginUiEvent()
    data class LoginPasswordChanged(val value: String) : LoginUiEvent()
    object Login : LoginUiEvent()
}
package cz.stepesove.simplechatapp.presentation.register

sealed class RegisterUiEvent {
    data class RegisterUsernameChanged(val value: String) : RegisterUiEvent()
    data class RegisterPasswordChanged(val value: String) : RegisterUiEvent()
    object Register : RegisterUiEvent()
}
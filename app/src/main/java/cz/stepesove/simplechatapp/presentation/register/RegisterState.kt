package cz.stepesove.simplechatapp.presentation.register

data class RegisterState(
    val isLoading: Boolean = false,
    val registerUsername: String = "",
    val registerPassword: String = ""
)
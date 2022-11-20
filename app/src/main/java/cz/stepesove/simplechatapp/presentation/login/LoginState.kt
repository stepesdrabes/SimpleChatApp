package cz.stepesove.simplechatapp.presentation.login

data class LoginState(
    val isLoading: Boolean = false,
    val loginUsername: String = "",
    val loginPassword: String = ""
)
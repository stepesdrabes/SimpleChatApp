package cz.stepesove.simplechatapp.data.remote.responses

data class TokenResponse(
    val token: String,
    val refreshToken: String,
    val user: UserResponse
)

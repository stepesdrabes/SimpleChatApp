package cz.stepesove.simplechatapp.data.remote.responses.auth

import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse

data class TokenResponse(
    val accessToken: String,
    val user: UserResponse,
    val expireDate: String
)

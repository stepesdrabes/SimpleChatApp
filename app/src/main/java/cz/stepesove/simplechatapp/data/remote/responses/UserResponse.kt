package cz.stepesove.simplechatapp.data.remote.responses

data class UserResponse(
    val id: String,
    val username: String,
    val displayName: String?,
    val imageName: String?,
    val registeredAt: String
)

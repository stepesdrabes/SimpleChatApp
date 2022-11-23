package cz.stepesove.simplechatapp.data.remote.responses.users

data class UserResponse(
    val id: String,
    val username: String,
    val displayName: String?,
    val imageName: String?,
    val registeredAt: String
)

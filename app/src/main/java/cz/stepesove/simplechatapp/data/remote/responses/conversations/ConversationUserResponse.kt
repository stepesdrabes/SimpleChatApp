package cz.stepesove.simplechatapp.data.remote.responses.conversations

import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse

class ConversationUserResponse(
    val id: Int,
    val nickname: String?,
    val user: UserResponse,
    val addedAt: String
)
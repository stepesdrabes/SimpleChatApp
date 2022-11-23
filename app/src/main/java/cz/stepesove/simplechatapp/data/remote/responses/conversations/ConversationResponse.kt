package cz.stepesove.simplechatapp.data.remote.responses.conversations

import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse

data class ConversationResponse(
    val id: String,
    val name: String,
    val imageName: String?,
    val owner: UserResponse,
    val lastMessage: ConversationMessageResponse? = null,
    val users: List<ConversationUserResponse>
)
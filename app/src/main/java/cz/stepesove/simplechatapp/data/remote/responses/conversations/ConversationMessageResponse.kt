package cz.stepesove.simplechatapp.data.remote.responses.conversations

data class ConversationMessageResponse(
    val id: String,
    val textContent: String,
    val imageName: String?,
    val author: ConversationUserResponse,
    val createdAt: String
)
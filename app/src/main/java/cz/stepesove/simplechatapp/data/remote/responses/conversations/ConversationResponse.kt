package cz.stepesove.simplechatapp.data.remote.responses.conversations

import android.os.Parcelable
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConversationResponse(
    val id: String,
    val name: String,
    val imageName: String?,
    val owner: UserResponse,
    val lastMessage: ConversationMessageResponse?,
    val conversationUsers: List<ConversationUserResponse>
) : Parcelable
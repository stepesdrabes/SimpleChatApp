package cz.stepesove.simplechatapp.data.remote.responses.conversations

import android.os.Parcelable
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
class ConversationUserResponse(
    val nickname: String?,
    val userId: String,
    val user: UserResponse,
    val conversationId: String?,
    val addedAt: String
) : Parcelable
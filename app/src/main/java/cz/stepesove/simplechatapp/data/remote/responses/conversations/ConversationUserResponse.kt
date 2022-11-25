package cz.stepesove.simplechatapp.data.remote.responses.conversations

import android.os.Parcelable
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import kotlinx.parcelize.Parcelize

@Parcelize
class ConversationUserResponse(
    val id: Int,
    val nickname: String?,
    val user: UserResponse,
    val addedAt: String
) : Parcelable
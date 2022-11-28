package cz.stepesove.simplechatapp.data.remote.responses.conversations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ConversationMessageResponse(
    val id: String,
    val textContent: String,
    val conversationId: String,
    val author: ConversationUserResponse,
    val createdAt: String
) : Parcelable
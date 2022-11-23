package cz.stepesove.simplechatapp.data.local.models.conversations

import okhttp3.MultipartBody

data class UpdateConversationModel(
    val name: String? = null,
    val imageFile: MultipartBody.Part? = null,
    val removeImage: Boolean? = null
)
package cz.stepesove.simplechatapp.data.local.models.conversations

import okhttp3.MultipartBody

data class CreateConversationModel(
    val name: String,
    val imageFile: MultipartBody.Part?,
    val users: List<String>
)
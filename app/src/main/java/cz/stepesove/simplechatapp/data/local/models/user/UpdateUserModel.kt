package cz.stepesove.simplechatapp.data.local.models.user

import okhttp3.MultipartBody

data class UpdateUserModel(
    val image: MultipartBody.Part? = null,
    val username: String? = null
)
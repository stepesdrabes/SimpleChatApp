package cz.stepesove.simplechatapp.data.local.models.auth

import okhttp3.MultipartBody

data class RegisterModel(
    val username: String,
    val password: String,
    val image: MultipartBody.Part?
)
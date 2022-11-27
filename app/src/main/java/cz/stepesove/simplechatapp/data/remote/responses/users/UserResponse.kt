package cz.stepesove.simplechatapp.data.remote.responses.users

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserResponse(
    val id: String,
    val username: String,
    val imageName: String?,
    val registeredAt: String
) : Parcelable

package cz.stepesove.simplechatapp.data.remote

object HttpRoutes {

    const val BASE_URL = "http://10.0.2.2:5000"
    private const val DEFAULT_AVATAR =
        "https://i0.wp.com/tleliteracy.com/wp-content/uploads/2017/02/default-avatar.png"

    private const val FILES_URL = "$BASE_URL/api/files"
    const val ONLINE_HUB_URL = "$BASE_URL/hubs/online"
    const val MESSAGES_HUB_URL = "$BASE_URL/hubs/messages"

    const val AUTH_SIGNUP_URL = "auth/signup"
    const val AUTH_SIGNIN_URL = "auth/signin"

    const val CURRENT_USER_URL = "user"
    const val PEOPLE_URL = "users"

    const val CONVERSATION_URL = "conversation"
    const val CONVERSATIONS_URL = "conversations"
    const val MESSAGES_URL = "messages"

    fun imageUrl(imageName: String) = "$FILES_URL/$imageName"
    fun userImageUrl(imageName: String?) = imageName?.let { "$FILES_URL/$it" } ?: DEFAULT_AVATAR
}
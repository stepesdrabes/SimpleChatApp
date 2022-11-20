package cz.stepesove.simplechatapp.data.remote.repositories.impl

import android.content.SharedPreferences
import cz.stepesove.simplechatapp.data.remote.api.ConversationApi
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository

class ConversationRepositoryImpl(
    private val conversationApi: ConversationApi,
    private val prefs: SharedPreferences
) : ConversationRepository {

}
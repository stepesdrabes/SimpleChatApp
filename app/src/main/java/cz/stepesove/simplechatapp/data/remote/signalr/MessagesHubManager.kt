package cz.stepesove.simplechatapp.data.remote.signalr

import android.content.SharedPreferences

class MessagesHubManager(
    private val sharedPreferences: SharedPreferences
) {

    private var _connected: Boolean = false

    fun connect() {

    }

    val connected: Boolean get() = _connected
}
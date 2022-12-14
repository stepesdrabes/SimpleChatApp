package cz.stepesove.simplechatapp.data.remote.signalr

import android.content.SharedPreferences
import androidx.compose.runtime.mutableStateListOf
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import cz.stepesove.simplechatapp.data.remote.HttpRoutes

class OnlineHubManager(
    private val sharedPreferences: SharedPreferences
) {

    private var connected: Boolean = false
    private var hubConnection: HubConnection? = null

    val onlineUsers = mutableStateListOf<String>()

    fun connect(appearAsOnline: Boolean) {
        if (connected) return

        val token = sharedPreferences.getString("jwt", null) ?: return
        val hub = HubConnectionBuilder
            .create(HttpRoutes.ONLINE_HUB_URL)
            .withHeader("Authorization", "Bearer $token")
            .withHeader("Online", appearAsOnline.toString())
            .withHandshakeResponseTimeout(5000)
            .build()

        println("Starting connection...")

        hub.start().blockingSubscribe {
            onlineUsers.clear()
            connected = hub.connectionState == HubConnectionState.CONNECTED
            if (connected) {
                addListeners(hub)
                hubConnection = hub

                println("Adding listeners...")
            }
        }
    }

    fun closeConnection() = hubConnection?.close()

    private fun addListeners(hub: HubConnection) {
        hub.on("OnlineUsers", { users: List<String> ->
            onlineUsers.clear()
            onlineUsers.addAll(users)
            println(users)
        }, List::class.java)

        hub.on("UserConnected", { userId: String ->
            if (onlineUsers.contains(userId)) return@on
            onlineUsers.add(userId)
        }, String::class.java)

        hub.on("UserDisconnected", { userId: String ->
            if (!onlineUsers.contains(userId)) return@on
            onlineUsers.remove(userId)
        }, String::class.java)

        hub.onClosed {
            println("Connection closed: ${it.message ?: "No message"}")
            onlineUsers.clear()
            connected = false
            hubConnection = null
        }
    }
}
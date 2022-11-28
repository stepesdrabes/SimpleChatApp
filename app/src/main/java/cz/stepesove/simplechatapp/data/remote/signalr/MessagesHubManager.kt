package cz.stepesove.simplechatapp.data.remote.signalr

import android.content.SharedPreferences
import com.microsoft.signalr.HubConnection
import com.microsoft.signalr.HubConnectionBuilder
import com.microsoft.signalr.HubConnectionState
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationMessageResponse
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class MessagesHubManager(
    private val sharedPreferences: SharedPreferences
) : CoroutineScope {

    override val coroutineContext = Job()

    private var connected: Boolean = false
    private var hubConnection: HubConnection? = null

    private val messagesChannel = Channel<ConversationMessageResponse>()
    val messageResults = messagesChannel.receiveAsFlow()

    private val conversationsChannel = Channel<ConversationResponse>()
    val conversationResults = conversationsChannel.receiveAsFlow()

    fun connect() {
        if (connected) return

        val token = sharedPreferences.getString("jwt", null) ?: return
        val hub = HubConnectionBuilder
            .create(HttpRoutes.MESSAGES_HUB_URL)
            .withHeader("Authorization", "Bearer $token")
            .withHandshakeResponseTimeout(5000)
            .build()

        println("Starting connection...")

        hub.start().blockingSubscribe {
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
        hub.on("MessageCreated", { message: ConversationMessageResponse ->
            launch {
                messagesChannel.send(message)
                print(message)
            }
        }, ConversationMessageResponse::class.java)

        hub.on("ConversationCreated", { conversation: ConversationResponse ->
            launch {
                conversationsChannel.send(conversation)
                print(conversation)
            }
        }, ConversationResponse::class.java)

        hub.onClosed {
            println("Connection closed: ${it.message ?: "No message"}")
            connected = false
            hubConnection = null
        }
    }
}
package cz.stepesove.simplechatapp.di

import cz.stepesove.simplechatapp.data.remote.signalr.MessagesHubManager
import cz.stepesove.simplechatapp.data.remote.signalr.OnlineHubManager
import org.koin.dsl.module

val SignalRModule = module {
    single { OnlineHubManager(get()) }
    single { MessagesHubManager(get()) }
}
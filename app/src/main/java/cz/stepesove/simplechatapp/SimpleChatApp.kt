package cz.stepesove.simplechatapp

import android.app.Application
import cz.stepesove.simplechatapp.di.RestApiModule
import cz.stepesove.simplechatapp.di.SignalRModule
import cz.stepesove.simplechatapp.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class SimpleChatApp : Application() {

    override fun onCreate() {
        super.onCreate()

        GlobalContext.startKoin {
            androidContext(this@SimpleChatApp)
            modules(
                module { single { this@SimpleChatApp } },
                RestApiModule,
                SignalRModule,
                ViewModelsModule
            )
        }
    }
}
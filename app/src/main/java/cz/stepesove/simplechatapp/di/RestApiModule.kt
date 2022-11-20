package cz.stepesove.simplechatapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import cz.stepesove.simplechatapp.data.remote.api.AuthApi
import cz.stepesove.simplechatapp.data.remote.api.ConversationApi
import cz.stepesove.simplechatapp.data.remote.api.PeopleApi
import cz.stepesove.simplechatapp.data.remote.repositories.AuthRepository
import cz.stepesove.simplechatapp.data.remote.repositories.ConversationRepository
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.repositories.impl.AuthRepositoryImpl
import cz.stepesove.simplechatapp.data.remote.repositories.impl.ConversationRepositoryImpl
import cz.stepesove.simplechatapp.data.remote.repositories.impl.PeopleRepositoryImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val RestApiModule = module {

    // Auth API
    single<AuthApi> {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    // People API
    single<PeopleApi> {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(PeopleApi::class.java)
    }

    // Conversation API
    single<ConversationApi> {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:5000/api/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(ConversationApi::class.java)
    }

    // Shared preferences
    single<SharedPreferences> {
        get<Application>().getSharedPreferences("prefs", Context.MODE_PRIVATE)
    }

    // Auth repository
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    // People repository
    single<PeopleRepository> { PeopleRepositoryImpl(get(), get()) }

    // Conversation repository
    single<ConversationRepository> { ConversationRepositoryImpl(get(), get()) }
}
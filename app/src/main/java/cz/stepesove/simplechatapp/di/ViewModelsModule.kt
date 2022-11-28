package cz.stepesove.simplechatapp.di

import cz.stepesove.simplechatapp.presentation.convo.ConvoViewModel
import cz.stepesove.simplechatapp.presentation.create_convo.CreateConvoViewModel
import cz.stepesove.simplechatapp.presentation.home.HomeViewModel
import cz.stepesove.simplechatapp.presentation.landing.LandingViewModel
import cz.stepesove.simplechatapp.presentation.login.LoginViewModel
import cz.stepesove.simplechatapp.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    viewModel { LandingViewModel(get(), get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
    viewModel { HomeViewModel(get(), get(), get(), get(), get()) }
    viewModel { CreateConvoViewModel(get(), get(), get()) }
    viewModel { ConvoViewModel(get(), get(), get()) }
}
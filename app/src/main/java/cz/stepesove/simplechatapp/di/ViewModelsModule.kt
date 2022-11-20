package cz.stepesove.simplechatapp.di

import cz.stepesove.simplechatapp.presentation.login.LoginViewModel
import cz.stepesove.simplechatapp.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterViewModel(get()) }
}
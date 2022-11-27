package cz.stepesove.simplechatapp.presentation.landing

import android.content.SharedPreferences
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.stepesove.simplechatapp.data.remote.repositories.PeopleRepository
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import kotlinx.coroutines.launch

class LandingViewModel(
    private val peopleRepository: PeopleRepository,
    sharedPreferences: SharedPreferences
) : ViewModel() {

    var autoSigningIn by mutableStateOf(false)
    var currentUser by mutableStateOf<UserResponse?>(null)

    init {
        if (sharedPreferences.getBoolean("options.staySignedIn", true)) autoSignIn()
    }

    private fun autoSignIn() {
        viewModelScope.launch {
            autoSigningIn = true
            currentUser = peopleRepository.currentUser().data
            autoSigningIn = false
        }
    }
}
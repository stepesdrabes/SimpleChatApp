package cz.stepesove.simplechatapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.presentation.destinations.LandingScreenDestination
import cz.stepesove.simplechatapp.presentation.home.components.NavigationBar
import cz.stepesove.simplechatapp.presentation.home.pages.ConvosPage
import cz.stepesove.simplechatapp.presentation.home.pages.OptionsPage
import cz.stepesove.simplechatapp.presentation.home.pages.PeoplePage
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
@Destination
fun HomeScreen(
    navigator: DestinationsNavigator
) {
    val homeViewModel: HomeViewModel = koinViewModel()
    var selectedIndex by remember { mutableStateOf(0) }

    if (!homeViewModel.currentUserLoading && homeViewModel.currentUserState == null) {
        navigator.navigate(LandingScreenDestination())
        return
    }

    if (homeViewModel.currentUserLoading) {
        Box(Modifier.fillMaxSize()) { CircularProgressIndicator() }
        return
    }

    val currentUser = homeViewModel.currentUserState!!

    Scaffold(
        bottomBar = {
            NavigationBar(
                selectedIndex = selectedIndex,
                pageSelected = { selectedIndex = it }
            )
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues = it)
        ) {
            when (selectedIndex) {
                0 -> ConvosPage(navigator = navigator, homeViewModel, currentUser) { selectedIndex = 2 }
                1 -> PeoplePage(navigator = navigator, homeViewModel, currentUser)
                2 -> OptionsPage(navigator = navigator, homeViewModel, currentUser)
            }
        }
    }
}
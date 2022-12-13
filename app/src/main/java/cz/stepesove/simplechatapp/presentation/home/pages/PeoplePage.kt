package cz.stepesove.simplechatapp.presentation.home.pages

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.home.HomeViewModel
import cz.stepesove.simplechatapp.presentation.home.components.PersonCard
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PeoplePage(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel,
    currentUser: UserResponse
) {
    val pullRefreshState =
        rememberPullRefreshState(homeViewModel.peopleLoading, { homeViewModel.loadPeople() })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
            contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
        ) {
            item(span = { GridItemSpan(2) }) {
                TitleBox(title = stringResource(id = R.string.people_title))
            }

            items(homeViewModel.peopleState.filter { it.id != currentUser.id }) { user ->
                PersonCard(
                    user,
                    online = homeViewModel.onlineHubManager.onlineUsers.contains(user.id)
                )
            }

            item(span = { GridItemSpan(2) }) {
                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
        }

        PullRefreshIndicator(
            homeViewModel.peopleLoading,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}
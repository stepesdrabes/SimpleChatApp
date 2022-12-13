package cz.stepesove.simplechatapp.presentation.home.pages

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.destinations.CreateConvoScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.HomeScreenDestination
import cz.stepesove.simplechatapp.presentation.home.HomeViewModel
import cz.stepesove.simplechatapp.presentation.home.components.conversation.ConversationCard
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConvosPage(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel,
    currentUser: UserResponse,
    profileClicked: () -> Unit,
) {
    val pullRefreshState = rememberPullRefreshState(
        homeViewModel.conversationsLoading,
        { homeViewModel.loadConversations() })

    val context = LocalContext.current

    LaunchedEffect(homeViewModel, context) {
        homeViewModel.messagesHubManager.conversationResults.collect { homeViewModel.loadConversations() }
        homeViewModel.messagesHubManager.messageResults.collect { homeViewModel.loadConversations() }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)) {
            item {
                Box {
                    TopAppBar(
                        contentPadding = PaddingValues(
                            start = MaterialTheme.spacing.pagePadding / 2,
                            end = MaterialTheme.spacing.pagePadding / 2
                        ),
                        backgroundColor = MaterialTheme.colors.background,
                        elevation = 0.dp
                    ) {
                        Box(
                            modifier = Modifier
                                .aspectRatio(1.0f)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable { profileClicked.invoke() }
                        ) {
                            RoundedImage(
                                modifier = Modifier.align(Alignment.Center),
                                size = 24.dp,
                                url = HttpRoutes.userImageUrl(currentUser.imageName)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1.0f))

                        Box(
                            modifier = Modifier
                                .aspectRatio(1.0f)
                                .clip(MaterialTheme.shapes.medium)
                                .clickable {
                                    navigator.navigate(CreateConvoScreenDestination(currentUser))
                                }
                        ) {
                            Icon(
                                modifier = Modifier.align(Alignment.Center),
                                painter = painterResource(id = R.drawable.ic_fi_br_add),
                                tint = MaterialTheme.colors.onBackground,
                                contentDescription = stringResource(id = R.string.app_name)
                            )
                        }
                    }

                    TitleBox(title = stringResource(id = R.string.convos_title))
                }
            }

            val convos = homeViewModel.conversationsState

            items(count = convos.size) { index ->
                val convo = convos[index]
                var online = false

                convo.conversationUsers.map { it.user.id }.forEach {
                    if (it != currentUser.id
                        && homeViewModel.onlineHubManager.onlineUsers.contains(it)
                    ) online = true
                }

                ConversationCard(
                    navigator = navigator,
                    currentUser = currentUser,
                    conversation = convo,
                    online = online
                )
            }
        }

        PullRefreshIndicator(
            homeViewModel.conversationsLoading,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}
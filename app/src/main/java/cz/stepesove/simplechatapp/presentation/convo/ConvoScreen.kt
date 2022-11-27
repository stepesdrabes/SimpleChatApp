package cz.stepesove.simplechatapp.presentation.convo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.convo.components.ConvoMessage
import cz.stepesove.simplechatapp.presentation.convo.components.NewMessageBar
import cz.stepesove.simplechatapp.presentation.destinations.UpdateConvoScreenDestination
import cz.stepesove.simplechatapp.presentation.home.components.conversation.ConversationIcon
import cz.stepesove.simplechatapp.presentation.shared.components.BackAppBar
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun ConvoScreen(
    currentUser: UserResponse,
    conversation: ConversationResponse,
    navigator: DestinationsNavigator
) {
    val viewModel: ConvoViewModel = koinViewModel()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        topBar = {
            BackAppBar(
                navigator = navigator,
                showBackText = false,
                titleContent = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        ConversationIcon(
                            size = 24.dp,
                            conversationImageName = conversation.imageName,
                            userImageNames = conversation.conversationUsers.map { it.user.imageName }
                        )

                        Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                        Text(
                            text = conversation.name,
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colors.onBackground,
                        )
                    }
                },
                moreAction = {
                    navigator.navigate(UpdateConvoScreenDestination())
                }
            )
        },
        bottomBar = {
            NewMessageBar(currentUser = currentUser)
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            LazyColumn(
                reverseLayout = true,
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
            ) {
                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.small))
                }

                items(count = 100) { index ->
                    ConvoMessage(index.toString())
                }

                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
                }
            }
        }
    }
}
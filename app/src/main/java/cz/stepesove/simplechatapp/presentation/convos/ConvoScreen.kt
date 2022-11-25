package cz.stepesove.simplechatapp.presentation.convos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.convos.components.NewMessageBar
import cz.stepesove.simplechatapp.presentation.create_convo.CreateConvoViewModel
import cz.stepesove.simplechatapp.presentation.home.components.conversation.ConversationIcon
import cz.stepesove.simplechatapp.presentation.shared.components.BackAppBar
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun ConvoScreen(
    currentUser: UserResponse,
    conversation: ConversationResponse,
    navigator: DestinationsNavigator
) {
    val viewModel: CreateConvoViewModel = koinViewModel()

    Scaffold(
        topBar = {
            BackAppBar(
                navigator = navigator,
                showBackText = false
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ConversationIcon(
                        size = 32.dp,
                        conversationImageName = conversation.imageName,
                        userImageNames = conversation.users.map { it.user.imageName }
                    )

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                    Text(
                        text = conversation.name,
                        fontSize = MaterialTheme.textSize.medium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
            }
        },
        bottomBar = {
            NewMessageBar()
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
            ) {

            }
        }
    }
}
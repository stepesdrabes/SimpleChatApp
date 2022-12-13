package cz.stepesove.simplechatapp.presentation.home.components.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.destinations.ConvoScreenDestination
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightGreen
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun ConversationCard(
    navigator: DestinationsNavigator,
    currentUser: UserResponse,
    conversation: ConversationResponse,
    online: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.pagePadding / 2)
            .clip(MaterialTheme.shapes.large)
            .clickable {
                println(conversation.conversationUsers.map { it.conversationId })
                navigator.navigate(
                    ConvoScreenDestination(
                        currentUser = currentUser,
                        conversation = conversation
                    )
                )
            }
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Row(
                modifier = Modifier.weight(1F),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ConversationIcon(
                    conversationImageName = conversation.imageName,
                    userImageNames = conversation.conversationUsers.map { it.user.imageName }
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                Column(
                    modifier = Modifier
                        .weight(1F)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    Text(
                        text = conversation.name,
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.textSize.medium,
                        fontWeight = FontWeight.Black
                    )

                    conversation.lastMessage?.let {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                        ) {
                            RoundedImage(
                                size = 12.dp,
                                url = HttpRoutes.userImageUrl(it.author.user.imageName)
                            )

                            Text(
                                text = it.textContent.take(28)
                                        + if (it.textContent.length > 28) "..." else "",
                                color = MaterialTheme.colors.onBackground.copy(alpha = 0.6F),
                                fontSize = MaterialTheme.textSize.normal,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                        }
                    }
                }
            }

            if (online) Box(
                modifier = Modifier
                    .size(14.dp)
                    .clip(CircleShape)
                    .background(HighlightGreen)
            )
        }
    }
}
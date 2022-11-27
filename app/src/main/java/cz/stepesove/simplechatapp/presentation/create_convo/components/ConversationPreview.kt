package cz.stepesove.simplechatapp.presentation.create_convo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.home.components.conversation.ConversationIcon
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun ConversationPreview(
    name: String,
    users: List<UserResponse>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
            .padding(
                horizontal = MaterialTheme.spacing.medium,
                vertical = MaterialTheme.spacing.small
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ConversationIcon(
                userImageNames = users.map { it.imageName },
                backgroundColor = MaterialTheme.colors.surface,
                borderColor = MaterialTheme.colors.background,
            )

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

            Text(
                text = name,
                color = MaterialTheme.colors.onBackground,
                fontSize = MaterialTheme.textSize.medium,
                fontWeight = FontWeight.Black
            )
        }
    }
}
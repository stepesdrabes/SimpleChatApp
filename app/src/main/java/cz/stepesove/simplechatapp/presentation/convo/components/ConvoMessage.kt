package cz.stepesove.simplechatapp.presentation.convo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightGreen
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun ConvoMessage(
    text: String,
    online: Boolean
    //currentUser: UserResponse,
    //conversationMessage: ConversationMessageResponse
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
    ) {
        Box {
            RoundedImage(
                size = 32.dp,
                url = HttpRoutes.userImageUrl(null)
            )

            if (online) Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .size(8.dp)
                    .clip(CircleShape)
                    .background(HighlightGreen)
            )
        }

        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 12.dp,
                        bottomEnd = 12.dp,
                        bottomStart = 12.dp
                    )
                )
                .background(HighlightBlue)
                .padding(
                    vertical = MaterialTheme.spacing.small,
                    horizontal = MaterialTheme.spacing.medium
                )
        ) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.textSize.normal
            )
        }
    }
}
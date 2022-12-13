package cz.stepesove.simplechatapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightGreen
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun PersonCard(
    user: UserResponse,
    online: Boolean
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1.0F)
            .clip(MaterialTheme.shapes.large)
            .background(MaterialTheme.colors.surface)
            .padding(MaterialTheme.spacing.medium)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box {
                RoundedImage(
                    size = 72.dp,
                    url = HttpRoutes.userImageUrl(user.imageName)
                )

                if (online) Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(16.dp)
                        .clip(CircleShape)
                        .background(HighlightGreen)
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

            Text(
                text = user.username,
                color = MaterialTheme.colors.onBackground,
                fontSize = MaterialTheme.textSize.large,
                fontWeight = FontWeight.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}
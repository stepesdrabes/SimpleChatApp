package cz.stepesove.simplechatapp.presentation.create_convo.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.*

@Composable
fun AddPersonCard(
    user: UserResponse,
    online: Boolean,
    selected: Boolean,
    onSelect: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.weight(1.0F),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1F),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large)
            ) {
                Box {
                    RoundedImage(
                        size = 48.dp,
                        url = HttpRoutes.userImageUrl(user.imageName)
                    )

                    if (online) Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .size(10.dp)
                            .clip(CircleShape)
                            .background(HighlightGreen)
                    )
                }

                Text(
                    text = user.username,
                    color = MaterialTheme.colors.onBackground,
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.Black
                )
            }
        }

        Box(
            modifier = Modifier
                .padding(
                    start = MaterialTheme.spacing.medium - MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.pagePadding - MaterialTheme.spacing.small
                )
                .clip(MaterialTheme.shapes.medium)
                .clickable { onSelect.invoke() }
                .padding(MaterialTheme.spacing.small)
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = if (selected) R.drawable.ic_fi_rr_minus else R.drawable.ic_fi_rr_plus),
                contentDescription = null,
                tint = if (selected) HighlightRed else HighlightBlue
            )
        }
    }
}
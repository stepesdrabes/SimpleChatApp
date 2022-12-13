package cz.stepesove.simplechatapp.presentation.home.components.conversation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.conversations.ConversationResponse
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.White
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun ConversationIcon(
    conversationImageName: String? = null,
    userImageNames: List<String?>,
    size: Dp = 72.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    borderColor: Color = MaterialTheme.colors.background
) {
    Box(
        modifier = Modifier.size(size)
    ) {
        conversationImageName?.let {
            RoundedImage(size = size / 2, url = HttpRoutes.imageUrl(it))
            return
        }

        when (userImageNames.size) {
            1 -> RoundedImage(
                size = size,
                url = HttpRoutes.userImageUrl(userImageNames.first())
            )
            2 -> userImageNames.forEachIndexed { index, conversationUser ->
                Box(
                    modifier = Modifier
                        .size(size * (2.toFloat() / 3))
                        .align(if (index % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd)
                ) {
                    ConversationRoundedImage(
                        size = size * (2.toFloat() / 3),
                        url = HttpRoutes.userImageUrl(conversationUser),
                        backgroundColor = backgroundColor,
                        borderColor = borderColor
                    )
                }
            }

            3 -> userImageNames.forEachIndexed { index, conversationUser ->
                ConversationRoundedImage(
                    modifier = Modifier.align(
                        when (index) {
                            0 -> Alignment.BottomCenter
                            1 -> Alignment.TopStart
                            else -> Alignment.TopEnd
                        }
                    ),
                    size = size * (3.toFloat() / 5),
                    url = HttpRoutes.userImageUrl(conversationUser),
                    backgroundColor = backgroundColor,
                    borderColor = borderColor
                )
            }

            else -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(userImageNames.size) { index ->
                    if (userImageNames.size > 4) {
                        if (index == 3) {
                            Box(
                                modifier = Modifier
                                    .size(size / 2)
                                    .clip(CircleShape)
                                    .background(HighlightBlue)
                                    .border(
                                        width = 2.dp,
                                        color = borderColor,
                                        shape = CircleShape
                                    )
                            ) {
                                Text(
                                    modifier = Modifier.align(Alignment.Center),
                                    text = "+${userImageNames.size - 3}",
                                    color = White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = MaterialTheme.textSize.small
                                )
                            }
                            return@items
                        }

                        if (index > 2) return@items
                    }

                    ConversationRoundedImage(
                        size = size / 2,
                        url = HttpRoutes.userImageUrl(userImageNames[index]),
                        strokeWidth = 2.dp,
                        backgroundColor = backgroundColor,
                        borderColor = borderColor
                    )
                }
            }
        }
    }
}
package cz.stepesove.simplechatapp.presentation.home.components.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun ConversationRoundedImage(
    modifier: Modifier = Modifier,
    size: Dp,
    url: String,
    contentDescription: String? = null,
    strokeWidth: Dp = 3.dp
) {
    Image(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .border(
                width = strokeWidth,
                color = MaterialTheme.colors.background,
                shape = CircleShape
            ),
        painter = rememberImagePainter(
            data = url,
            builder = {
                crossfade(true)
                crossfade(500)
                transformations(CircleCropTransformation())
            }
        ),
        contentDescription = contentDescription
    )
}
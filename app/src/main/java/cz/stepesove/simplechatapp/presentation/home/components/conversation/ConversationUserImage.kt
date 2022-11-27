package cz.stepesove.simplechatapp.presentation.home.components.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun ConversationRoundedImage(
    modifier: Modifier = Modifier,
    size: Dp,
    url: String,
    contentDescription: String? = null,
    strokeWidth: Dp = 3.dp,
    backgroundColor: Color = MaterialTheme.colors.surface,
    borderColor: Color = MaterialTheme.colors.background
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(
                width = strokeWidth,
                color = borderColor,
                shape = CircleShape
            )
    ) {
        Image(
            modifier = modifier
                .align(Alignment.Center)
                .size(size - 2.dp),
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = url)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        crossfade(500)
                        transformations(CircleCropTransformation())
                    }).build()
            ),
            contentDescription = contentDescription
        )
    }
}
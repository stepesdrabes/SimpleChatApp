package cz.stepesove.simplechatapp.presentation.shared.components

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation

@Composable
fun RoundedImage(
    modifier: Modifier = Modifier,
    size: Dp,
    url: String? = null,
    bitmap: Bitmap? = null,
    painter: Painter? = null,
    contentDescription: String? = null
) {
    if (url == null) return Box(modifier = Modifier.size(size))

    Image(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface),
        painter = painter ?: rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current).data(data = bitmap ?: url)
                .apply(block = fun ImageRequest.Builder.() {
                    crossfade(true)
                    crossfade(500)
                    transformations(CircleCropTransformation())
                }).build()
        ),
        contentDescription = contentDescription
    )
}
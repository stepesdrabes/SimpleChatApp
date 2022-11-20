package cz.stepesove.simplechatapp.presentation.register.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage

@Composable
fun ChooseAvatar(
    size: Dp = 128.dp,
    currentImageUrl: String?
) {
    var chosenUrl by remember { mutableStateOf<Uri?>(null) }
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            chosenUrl = uri
        }

    Box(
        Modifier
            .size(size)
            .clip(CircleShape)
            .background(MaterialTheme.colors.surface)
            .clickable {
                galleryLauncher.launch("image/*")
            }
    ) {
        if (currentImageUrl == null && chosenUrl == null) Icon(
            modifier = Modifier
                .size(size * (1 / 3.toFloat()))
                .align(Alignment.Center),
            painter = painterResource(id = R.drawable.ic_fi_rr_mode_portrait),
            tint = MaterialTheme.colors.onSurface,
            contentDescription = null
        )

        currentImageUrl?.let {
            if (chosenUrl == null) RoundedImage(
                size = size,
                url = it
            )
        }

        if (chosenUrl != null) Image(
            painter = rememberAsyncImagePainter(chosenUrl),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
            modifier = Modifier.size(size)
        )
    }
}
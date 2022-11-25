package cz.stepesove.simplechatapp.presentation.convos.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelTextField
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing

@Composable
fun NewMessageBar() {
    val surfaceColor = MaterialTheme.colors.surface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colors.background)
            .drawBehind {
                drawLine(
                    brush = SolidColor(surfaceColor),
                    strokeWidth = 1f,
                    cap = StrokeCap.Square,
                    start = Offset.Zero.copy(y = 0f),
                    end = Offset(x = size.width, y = 0f)
                )
            }
    ) {
        Row {
            IconLabelTextField(
                modifier = Modifier.weight(1f),
                label = "New message",
                onValueChange = {}
            )

            Box(
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {

                    }
                    .padding(MaterialTheme.spacing.small)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.ic_fi_br_paper_plane),
                    contentDescription = null
                )
            }
        }
    }
}
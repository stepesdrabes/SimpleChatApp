package cz.stepesove.simplechatapp.presentation.shared.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun TitleBox(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = MaterialTheme.spacing.pagePadding)
            .padding(
                top = MaterialTheme.spacing.extraLarge,
                bottom = MaterialTheme.spacing.medium
            )
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = title,
            fontSize = MaterialTheme.textSize.title,
            fontWeight = FontWeight.Black,
            color = HighlightBlue,
            textAlign = TextAlign.Center
        )
    }
}
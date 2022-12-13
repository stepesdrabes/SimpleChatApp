package cz.stepesove.simplechatapp.presentation.about_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun AppAuthors() {
    Column(
        modifier = Modifier.padding(start = MaterialTheme.spacing.small)
    ) {
        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = stringResource(id = R.string.about_app_author_info),
            fontSize = MaterialTheme.textSize.medium,
            fontWeight = FontWeight.ExtraBold,
            color = MaterialTheme.colors.onBackground
        )

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        val authorNames = stringArrayResource(id = R.array.about_app_author_names)
        val authorPictures = stringArrayResource(id = R.array.about_app_author_pictures)

        Column(
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            authorNames.forEachIndexed { index, name ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                ) {
                    RoundedImage(
                        size = 64.dp,
                        url = authorPictures[index]
                    )

                    Text(
                        text = name,
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.onBackground,
                    )
                }
            }
        }
    }
}
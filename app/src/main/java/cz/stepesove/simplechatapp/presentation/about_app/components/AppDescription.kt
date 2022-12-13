package cz.stepesove.simplechatapp.presentation.about_app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun AppDescription() {
    Column {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                painter = painterResource(id = R.drawable.simplechatapp_logo),
                tint = MaterialTheme.colors.onBackground,
                contentDescription = null
            )

            Text(
                text = stringResource(id = R.string.app_name),
                fontSize = MaterialTheme.textSize.large,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colors.onBackground,
            )
        }

        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        Text(
            modifier = Modifier.padding(start = MaterialTheme.spacing.textOffset),
            text = stringResource(id = R.string.about_app_description),
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
        )
    }
}
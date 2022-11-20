package cz.stepesove.simplechatapp.presentation.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun BackAppBar(
    navigator: DestinationsNavigator
) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.background,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding / 2),
        elevation = 0.dp,
        content = {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        navigator.navigateUp()
                    }
                    .padding(horizontal = MaterialTheme.spacing.pagePadding / 2),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_fi_br_arrow_left),
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = stringResource(id = R.string.back)
                )

                Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))

                Text(
                    text = stringResource(id = R.string.back),
                    fontSize = MaterialTheme.textSize.medium,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colors.onBackground,
                )
            }
        }
    )
}
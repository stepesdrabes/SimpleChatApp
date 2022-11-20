package cz.stepesove.simplechatapp.presentation.create_convo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.components.BackAppBar
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing

@Destination
@Composable
fun CreateConvoScreen(
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            BackAppBar(navigator = navigator)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraLarge),
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
            ) {
                item { TitleBox(title = stringResource(id = R.string.create_convo_title)) }

                // Bottom space
                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
            }
        }
    }
}
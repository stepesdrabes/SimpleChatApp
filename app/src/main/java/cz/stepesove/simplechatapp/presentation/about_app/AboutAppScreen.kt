package cz.stepesove.simplechatapp.presentation.about_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.about_app.components.AppDescription
import cz.stepesove.simplechatapp.presentation.about_app.components.AppAuthors
import cz.stepesove.simplechatapp.presentation.about_app.components.AppLibrariesList
import cz.stepesove.simplechatapp.presentation.shared.components.BackAppBar
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelButton
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing

@Composable
@Destination
fun AboutAppScreen(
    navigator: DestinationsNavigator
) {
    val urlHandler = LocalUriHandler.current

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
                item { TitleBox(title = stringResource(id = R.string.about_app_title)) }
                item { AppDescription() }
                item { AppLibrariesList() }

                item {
                    val githubUri = stringResource(id = R.string.app_github_link)

                    IconLabelButton(
                        label = stringResource(id = R.string.about_app_open_github_repo),
                        iconId = R.drawable.ic_fi_rr_arrow_right,
                        color = HighlightBlue,
                        onClick = {
                            urlHandler.openUri(githubUri)
                        }
                    )
                }

                item { AppAuthors() }

                // Bottom space
                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.large)) }
            }
        }
    }
}
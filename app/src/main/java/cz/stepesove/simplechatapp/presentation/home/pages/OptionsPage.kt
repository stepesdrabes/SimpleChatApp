package cz.stepesove.simplechatapp.presentation.home.pages

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.destinations.LandingScreenDestination
import cz.stepesove.simplechatapp.presentation.home.HomeViewModel
import cz.stepesove.simplechatapp.presentation.shared.components.ChooseAvatar
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelButton
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightRed
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@OptIn(
    ExperimentalFoundationApi::class, ExperimentalMaterialApi::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun OptionsPage(
    navigator: DestinationsNavigator,
    homeViewModel: HomeViewModel,
    currentUser: UserResponse
) {
    val signOutClicked = remember { mutableStateOf(false) }
    val saveChangesClicked = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current

    LazyColumn(
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding),
    ) {
        item { TitleBox(title = stringResource(id = R.string.profile_title)) }

        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                ChooseAvatar(currentImageUrl = HttpRoutes.userImageUrl(currentUser.imageName))

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                Text(
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.pagePadding),
                    text = stringResource(id = R.string.register_choose_profile_image_label),
                    color = MaterialTheme.colors.onSurface,
                    fontSize = MaterialTheme.textSize.normal,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large * 2))

                IconLabelButton(
                    label = stringResource(id = R.string.options_save_profile_changes_button_label),
                    iconId = R.drawable.ic_fi_rr_check,
                    enabled = !saveChangesClicked.value,
                    onClick = {
                        keyboardController?.hide()
                        saveChangesClicked.value = true
                    },
                    color = HighlightBlue
                )
            }
        }

        item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) }

        item { TitleBox(title = stringResource(id = R.string.options_title)) }

        item {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Column(
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.surface),
                ) {
                    Row(
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.pagePadding,
                            vertical = MaterialTheme.spacing.small / 2,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1.0F),
                            text = "Stay signed in",
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.Black
                        )

                        Switch(
                            checked = homeViewModel.optionStaySignedIn,
                            onCheckedChange = { homeViewModel.toggleOptionStaySignedIn() },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = HighlightBlue,
                                checkedTrackColor = HighlightBlue,
                                uncheckedTrackColor = MaterialTheme.colors.background,
                                uncheckedThumbColor = MaterialTheme.colors.background
                            )
                        )
                    }

                    Row(
                        modifier = Modifier.padding(
                            horizontal = MaterialTheme.spacing.pagePadding,
                            vertical = MaterialTheme.spacing.small / 2,
                        ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1.0F),
                            text = "Appear as online",
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.Black
                        )

                        Switch(
                            checked = homeViewModel.optionsAppearAsOnline,
                            onCheckedChange = { homeViewModel.toggleOptionAppearAsOnline() },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = HighlightBlue,
                                checkedTrackColor = HighlightBlue,
                                uncheckedTrackColor = MaterialTheme.colors.background,
                                uncheckedThumbColor = MaterialTheme.colors.background
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.large * 2))

                IconLabelButton(
                    label = stringResource(id = R.string.options_sign_out_button_label),
                    iconId = R.drawable.ic_fi_rr_key,
                    enabled = !signOutClicked.value,
                    onClick = {
                        signOutClicked.value = true
                        homeViewModel.signOut {
                            homeViewModel.onlineHubManager.closeConnection()
                            homeViewModel.messagesHubManager.closeConnection()
                            navigator.popBackStack()
                            navigator.navigate(LandingScreenDestination())
                        }
                    },
                    color = HighlightRed
                )

                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
            }
        }
    }
}
package cz.stepesove.simplechatapp.presentation.register

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.RequestResult
import cz.stepesove.simplechatapp.presentation.destinations.HomeScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.LoginScreenDestination
import cz.stepesove.simplechatapp.presentation.shared.components.ChooseAvatar
import cz.stepesove.simplechatapp.presentation.shared.components.BackAppBar
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelButton
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelTextField
import cz.stepesove.simplechatapp.presentation.shared.components.TitleBox
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Destination
@Composable
fun RegisterScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: RegisterViewModel = koinViewModel()
    val state = viewModel.state
    val context = LocalContext.current

    val error = stringResource(id = R.string.unknown_error_occurred)
    LaunchedEffect(viewModel, context) {
        viewModel.authResults.collect { result ->
            when (result) {
                is RequestResult.Ok -> navigator.navigate(HomeScreenDestination) {
                    popUpTo(LoginScreenDestination.route) {
                        inclusive = true
                    }
                }
                is RequestResult.Unauthorized -> Toast.makeText(
                    context,
                    "You're not authorized",
                    Toast.LENGTH_LONG
                ).show()
                else -> Toast.makeText(context, error, Toast.LENGTH_LONG).show()
            }
        }
    }

    Scaffold(
        topBar = {
            BackAppBar(navigator = navigator)
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.large * 2),
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
            ) {
                // Title
                item { TitleBox(title = stringResource(id = R.string.register_title)) }

                // Choose avatar
                item {
                    Column(
                        modifier = Modifier.padding(horizontal = MaterialTheme.spacing.pagePadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                    ) {
                        ChooseAvatar(currentImageUrl = null)

                        Text(
                            text = stringResource(id = R.string.register_choose_profile_image_label),
                            color = MaterialTheme.colors.onSurface,
                            fontSize = MaterialTheme.textSize.normal,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                // Input fields
                item {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
                    ) {
                        IconLabelTextField(
                            iconId = R.drawable.ic_fi_rr_user,
                            label = stringResource(id = R.string.register_input_username_label)
                        ) { newText ->
                            viewModel.onEvent(RegisterUiEvent.RegisterUsernameChanged(newText.text))
                        }

                        IconLabelTextField(
                            iconId = R.drawable.ic_fi_rr_lock,
                            label = stringResource(id = R.string.register_input_password_label),
                            isPassword = true
                        ) { newText ->
                            viewModel.onEvent(RegisterUiEvent.RegisterPasswordChanged(newText.text))
                        }
                    }
                }

                // Sign up button
                item {
                    IconLabelButton(
                        label = stringResource(id = R.string.register_register_button_label),
                        iconId = R.drawable.ic_fi_rr_sign_in,
                        enabled = !state.isLoading,
                        onClick = {
                            viewModel.onEvent(RegisterUiEvent.Register)
                        },
                        color = HighlightBlue,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
                }
            }
        }
    }
}
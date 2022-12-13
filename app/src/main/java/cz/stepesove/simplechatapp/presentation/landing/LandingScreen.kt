package cz.stepesove.simplechatapp.presentation.landing

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.destinations.AboutAppScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.HomeScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.LoginScreenDestination
import cz.stepesove.simplechatapp.presentation.destinations.RegisterScreenDestination
import cz.stepesove.simplechatapp.presentation.shared.components.IconLabelButton
import cz.stepesove.simplechatapp.presentation.shared.theme.HighlightBlue
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize
import org.koin.androidx.compose.koinViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@RootNavGraph(start = true)
@Destination
@Composable
fun LandingScreen(
    navigator: DestinationsNavigator
) {
    val viewModel: LandingViewModel = koinViewModel()

    if (!viewModel.autoSigningIn && viewModel.currentUser != null) {
        navigator.popBackStack()
        navigator.navigate(HomeScreenDestination())
        return
    }

    if (viewModel.autoSigningIn) {
        Box(Modifier.fillMaxSize()) { CircularProgressIndicator() }
        return
    }

    val loginClicked = remember { mutableStateOf(false) }
    val registerClicked = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding / 2),
                backgroundColor = MaterialTheme.colors.background,
                elevation = 0.dp
            ) {
                Spacer(modifier = Modifier.weight(1.0f))

                Box(
                    modifier = Modifier
                        .aspectRatio(1.0f)
                        .clip(MaterialTheme.shapes.medium)
                        .clickable {
                            navigator.navigate(AboutAppScreenDestination())
                        }
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_fi_br_info),
                        tint = MaterialTheme.colors.onBackground,
                        contentDescription = stringResource(id = R.string.app_name)
                    )
                }
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = MaterialTheme.spacing.pagePadding,
                        end = MaterialTheme.spacing.pagePadding,
                        bottom = MaterialTheme.spacing.large,
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.spacing.pagePadding)
                        .align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(128.dp),
                        painter = painterResource(id = R.drawable.simplechatapp_logo),
                        tint = HighlightBlue,
                        contentDescription = stringResource(id = R.string.app_name)
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = MaterialTheme.colors.onBackground,
                        fontSize = MaterialTheme.textSize.title,
                        fontWeight = FontWeight.Black,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                    Text(
                        text = stringResource(id = R.string.landing_title_text),
                        color = MaterialTheme.colors.onSurface,
                        fontSize = MaterialTheme.textSize.normal,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ) {
                    IconLabelButton(
                        label = stringResource(id = R.string.landing_register_button_label),
                        iconId = R.drawable.ic_fi_rr_sign_in,
                        enabled = !registerClicked.value,
                        onClick = {
                            registerClicked.value = true
                            navigator.navigate(RegisterScreenDestination())
                        },
                        color = MaterialTheme.colors.onBackground,
                        textColor = MaterialTheme.colors.background,
                    )

                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                    IconLabelButton(
                        label = stringResource(id = R.string.landing_login_button_label),
                        iconId = R.drawable.ic_fi_rr_key,
                        enabled = !loginClicked.value,
                        onClick = {
                            loginClicked.value = true
                            navigator.navigate(LoginScreenDestination())
                        },
                        color = HighlightBlue
                    )
                }
            }
        }
    }
}
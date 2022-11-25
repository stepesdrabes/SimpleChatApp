package cz.stepesove.simplechatapp.presentation.create_convo

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.create_convo.components.AddPersonCard
import cz.stepesove.simplechatapp.presentation.create_convo.components.ConversationPreview
import cz.stepesove.simplechatapp.presentation.shared.components.*
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize
import org.koin.androidx.compose.koinViewModel

@Destination
@Composable
fun CreateConvoScreen(
    currentUser: UserResponse,
    navigator: DestinationsNavigator
) {
    val viewModel: CreateConvoViewModel = koinViewModel()

    Scaffold(
        topBar = { BackAppBar(navigator = navigator) },
    ) {
        Box(modifier = Modifier.padding(it)) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.pagePadding)
            ) {
                item { TitleBox(title = stringResource(id = R.string.create_convo_title)) }

                item {
                    Column {
                        Column(
                            modifier = Modifier.padding(horizontal = MaterialTheme.spacing.pagePadding),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            ChooseAvatar(
                                currentImageUrl = null,
                                iconId = R.drawable.ic_fi_rr_mode_landscape
                            )

                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

                            Text(
                                text = stringResource(id = R.string.create_convo_choose_convo_image_label),
                                color = MaterialTheme.colors.onSurface,
                                fontSize = MaterialTheme.textSize.normal,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                        IconLabelTextField(
                            iconId = R.drawable.ic_fi_rr_id_badge,
                            label = stringResource(R.string.create_convo_input_name_label),
                            onValueChange = { value -> viewModel.convoName = value }
                        )

                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))

                        IconLabelButton(
                            label = stringResource(id = R.string.create_convo_create_button_label),
                            iconId = R.drawable.ic_fi_rr_check,
                            enabled = viewModel.convoName.text.isNotEmpty(),
                            onClick = { viewModel.createConversation() }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large * 2))
                }

                if (viewModel.selectedUsers.isNotEmpty() && viewModel.convoName.text.isNotEmpty()) {
                    item {
                        Column {
                            Text(
                                text = stringResource(id = R.string.create_convo_preview_title),
                                fontWeight = FontWeight.Black,
                                fontSize = MaterialTheme.textSize.large,
                                color = MaterialTheme.colors.onBackground
                            )

                            Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))

                            ConversationPreview(
                                name = viewModel.convoName.text,
                                users = viewModel.selectedUsers
                            )
                        }
                    }

                    item {
                        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large * 2))
                    }
                }

                item {
                    Text(
                        text = stringResource(id = R.string.create_convo_add_people_title).replace(
                            "{selected}",
                            viewModel.selectedUsers.size.toString()
                        ),
                        fontWeight = FontWeight.Black,
                        fontSize = MaterialTheme.textSize.large,
                        color = MaterialTheme.colors.onBackground
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
                }

                if (viewModel.peopleLoading) {
                    item { CircularProgressIndicator() }
                }

                if (!viewModel.peopleLoading) {
                    val people = viewModel.peopleState.toMutableList()
                    people.removeIf { user -> user.id == currentUser.id }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
                        ) {
                            people.forEach { user ->
                                AddPersonCard(
                                    user = user,
                                    selected = viewModel.selectedUsers.contains(user)
                                ) {
                                    if (viewModel.selectedUsers.contains(user)) viewModel.selectedUsers.remove(
                                        user
                                    )
                                    else viewModel.selectedUsers.add(user)
                                }
                            }
                        }
                    }
                }

                // Bottom space
                item { Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge)) }
            }
        }
    }
}
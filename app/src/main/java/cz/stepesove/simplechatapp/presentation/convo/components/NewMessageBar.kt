package cz.stepesove.simplechatapp.presentation.convo.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.data.remote.HttpRoutes
import cz.stepesove.simplechatapp.data.remote.responses.users.UserResponse
import cz.stepesove.simplechatapp.presentation.shared.components.RoundedImage
import cz.stepesove.simplechatapp.presentation.shared.state.KeyboardState
import cz.stepesove.simplechatapp.presentation.shared.state.keyboardAsState
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun NewMessageBar(
    value: String,
    currentUser: UserResponse,
    sending: Boolean,
    onValueChanged: (String) -> Unit,
    onSend: () -> Unit
) {
    val keyboardState by keyboardAsState()

    if (sending) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .background(MaterialTheme.colors.background)
                .padding(all = MaterialTheme.spacing.pagePadding / 2)
        ) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }

        return
    }

    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(all = MaterialTheme.spacing.pagePadding / 2)
            .blur(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedVisibility(
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            visible = keyboardState == KeyboardState.Closed
        ) {
            RoundedImage(
                size = 32.dp,
                url = HttpRoutes.userImageUrl(currentUser.imageName)
            )
        }

        AnimatedVisibility(
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            visible = keyboardState == KeyboardState.Closed
        ) {
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        }

        TextField(
            modifier = Modifier.weight(1F),
            value = value,
            shape = MaterialTheme.shapes.medium,
            textStyle = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.textSize.normal
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            trailingIcon = {
                Box(
                    modifier = Modifier
                        .padding(
                            start = MaterialTheme.spacing.medium - MaterialTheme.spacing.small,
                            end = MaterialTheme.spacing.pagePadding - MaterialTheme.spacing.small
                        )
                        .clip(MaterialTheme.shapes.medium)
                        .clickable { if (value.isNotEmpty()) onSend.invoke() }
                        .padding(MaterialTheme.spacing.small)
                ) {
                    Icon(
                        modifier = Modifier.align(Alignment.Center),
                        painter = painterResource(id = R.drawable.ic_fi_br_paper_plane),
                        contentDescription = null
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    text = "New message",
                    fontWeight = FontWeight.Medium,
                    fontSize = MaterialTheme.textSize.normal
                )
            },
            maxLines = 8,
            onValueChange = { newText ->
                onValueChanged.invoke(newText)
            }
        )
    }
}
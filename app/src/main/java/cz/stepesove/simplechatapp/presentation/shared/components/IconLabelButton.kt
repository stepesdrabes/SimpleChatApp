package cz.stepesove.simplechatapp.presentation.shared.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun IconLabelButton(
    modifier: Modifier = Modifier,
    label: String,
    iconId: Int,
    onClick: () -> Unit,
    enabled: Boolean = true,
    color: Color = MaterialTheme.colors.secondary,
    textColor: Color = MaterialTheme.colors.onSecondary,
    disabledColor: Color = MaterialTheme.colors.surface,
    onDisabledColor: Color = MaterialTheme.colors.onSurface,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Button(
        modifier = modifier
            .height(56.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            focusedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = textColor,
            disabledBackgroundColor = disabledColor,
            disabledContentColor = onDisabledColor,
        ),
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        Text(
            text = label,
            fontSize = MaterialTheme.textSize.normal,
            fontWeight = FontWeight.Bold
        )

        AnimatedVisibility(
            enter = expandHorizontally(),
            exit = shrinkHorizontally(),
            visible = enabled && !isPressed
        ) {
            Row {
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.small))

                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = iconId),
                    contentDescription = label
                )
            }
        }
    }
}
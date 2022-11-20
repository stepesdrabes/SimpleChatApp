package cz.stepesove.simplechatapp.presentation.shared.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun IconLabelTextField(
    iconId: Int,
    label: String,
    isPassword: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit
) {
    var value by remember { mutableStateOf(TextFieldValue("")) }
    var visible by remember { mutableStateOf(false) }

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        shape = MaterialTheme.shapes.medium,
        textStyle = TextStyle(
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.textSize.normal
        ),
        keyboardOptions = KeyboardOptions(keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text),
        leadingIcon = {
            Icon(
                modifier = Modifier.padding(
                    start = MaterialTheme.spacing.pagePadding,
                    end = MaterialTheme.spacing.medium
                ),
                painter = painterResource(id = iconId),
                contentDescription = null
            )
        },
        visualTransformation = if (!isPassword || visible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            if (!isPassword) return@TextField

            Box(
                modifier = Modifier
                    .padding(
                        start = MaterialTheme.spacing.medium - MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.pagePadding - MaterialTheme.spacing.small
                    )
                    .clip(MaterialTheme.shapes.medium)
                    .clickable {
                        visible = !visible
                    }
                    .padding(MaterialTheme.spacing.small)
            ) {
                Icon(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = if (visible) R.drawable.ic_fi_rr_eye_crossed else R.drawable.ic_fi_rr_eye),
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
                text = label,
                fontWeight = FontWeight.Medium,
                fontSize = MaterialTheme.textSize.normal
            )
        },
        singleLine = true,
        onValueChange = { newText ->
            value = newText
            onValueChange(newText)
        }
    )
}
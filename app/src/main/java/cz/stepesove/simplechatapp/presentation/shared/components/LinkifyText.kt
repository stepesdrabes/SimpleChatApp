package cz.stepesove.simplechatapp.presentation.shared.components

import android.util.Patterns
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun LinkifyText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = MaterialTheme.textSize.normal,
    color: Color = MaterialTheme.colors.onBackground,
    fontWeight: FontWeight = FontWeight.Normal,
    textOverflow: TextOverflow = TextOverflow.Visible,
    maxLines: Int = 16
) {
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val linksList = extractUrls(text)

    val annotatedString = buildAnnotatedString {
        append(text)
        linksList.forEach {
            addStyle(
                style = SpanStyle(
                    textDecoration = TextDecoration.Underline
                ),
                start = it.start,
                end = it.end
            )
            addStringAnnotation(
                tag = "URL",
                annotation = it.url,
                start = it.start,
                end = it.end
            )
        }
    }

    Text(
        text = annotatedString,
        fontSize = fontSize,
        fontWeight = fontWeight,
        color = color,
        overflow = textOverflow,
        maxLines = maxLines,
        modifier = modifier.pointerInput(Unit) {
            detectTapGestures { offsetPosition ->
                layoutResult.value?.let {
                    val position = it.getOffsetForPosition(offsetPosition)
                    annotatedString.getStringAnnotations(position, position).firstOrNull()
                        ?.let { result -> if (result.tag == "URL") uriHandler.openUri(result.item) }
                }
            }
        },
        onTextLayout = { layoutResult.value = it }
    )
}

private fun extractUrls(text: String): List<LinkInfo> {
    val matcher = Patterns.WEB_URL.matcher(text)
    var matchStart: Int
    var matchEnd: Int
    val links = arrayListOf<LinkInfo>()

    while (matcher.find()) {
        matchStart = matcher.start(1)
        matchEnd = matcher.end()

        var url = text.substring(matchStart, matchEnd)

        if (!url.startsWith("http://") && !url.startsWith("https://")) url = "https://$url"
        links.add(LinkInfo(url, matchStart, matchEnd))
    }

    return links
}

data class LinkInfo(
    val url: String,
    val start: Int,
    val end: Int
)
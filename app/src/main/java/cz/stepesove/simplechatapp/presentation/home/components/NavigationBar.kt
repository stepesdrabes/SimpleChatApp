package cz.stepesove.simplechatapp.presentation.home.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cz.stepesove.simplechatapp.R
import cz.stepesove.simplechatapp.presentation.shared.theme.spacing
import cz.stepesove.simplechatapp.presentation.shared.theme.textSize

@Composable
fun NavigationBar(
    selectedIndex: Int,
    pageSelected: (Int) -> Unit
) {
    val icons = listOf(
        NavigationBarItem(R.drawable.ic_fi_br_comments, "Convos"),
        NavigationBarItem(R.drawable.ic_fi_br_users_alt, "People"),
        NavigationBarItem(R.drawable.ic_fi_br_settings, "Options"),
    )

    val indicatorWidth = 50
    val indicatorHeight = 4
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp
    val buttonWidth = (screenWidth / icons.size)
    val desiredIndicatorX = buttonWidth * selectedIndex + buttonWidth / 2 - indicatorWidth / 2
    val indicatorX by animateIntAsState(targetValue = desiredIndicatorX)
    val surfaceColor = MaterialTheme.colors.surface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .background(MaterialTheme.colors.background)
            .drawBehind {
                val strokeWidth = 1.5f

                drawLine(
                    brush = SolidColor(surfaceColor),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Square,
                    start = Offset(x = 0f, y = 0f),
                    end = Offset(x = size.width, y = 0f)
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                icons.forEachIndexed { index, navBarItem ->
                    Box(
                        modifier = Modifier
                            .height(64.dp)
                            .weight(1.0f)
                            .clip(MaterialTheme.shapes.medium)
                            .clickable { pageSelected(index) }
                    ) {
                        Column(
                            modifier = Modifier.align(Alignment.Center),
                            horizontalAlignment = CenterHorizontally
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = navBarItem.icon),
                                tint = if (selectedIndex == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
                                contentDescription = null
                            )

                            AnimatedVisibility(visible = selectedIndex == index) {
                                Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraSmall))
                            }

                            AnimatedVisibility(visible = selectedIndex == index) {
                                Text(
                                    text = navBarItem.label,
                                    fontWeight = FontWeight.Black,
                                    fontSize = MaterialTheme.textSize.small,
                                    color = if (selectedIndex == index) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground,
                                )
                            }
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .width(indicatorWidth.dp)
                    .offset(x = indicatorX.dp)
                    .height(indicatorHeight.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colors.primary)
            )
        }
    }
}

data class NavigationBarItem(
    val icon: Int,
    val label: String
)
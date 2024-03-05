package com.rocknhoney.matcheslistingapp.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MAIN_COLOR

/**
 * Composable function responsible for rendering a result component displaying the scores of two teams.
 *
 * @param modifier The modifier for the ResultComponent layout.
 * @param firstTeamScore The score of the first team.
 * @param secondTeamScore The score of the second team.
 * @param backgroundColor The background color of the result component.
 * @param textColor The text color of the result component.
 * @param textStyle The text style of the result component.
 */
@Composable
fun ResultComponent(
    modifier: Modifier = Modifier,
    firstTeamScore: Int? = null,
    secondTeamScore: Int? = null,
    backgroundColor: Color = MAIN_COLOR,
    textColor: Color = Color.White,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
) {
    Box(
        modifier.background(
            color = backgroundColor,
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Text(
            text = "$firstTeamScore - $secondTeamScore",
            color = textColor,
            style = textStyle,
            modifier = Modifier.padding(
                horizontal = if (backgroundColor == MAIN_COLOR) 8.dp else 0.dp,
                vertical = 4.dp
            )
        )
    }
}
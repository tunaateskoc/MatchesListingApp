package com.rocknhoney.matcheslistingapp.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.rocknhoney.matcheslistingapp.R
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MAIN_COLOR

/**
 * Composable function responsible for rendering a row in the score board, displaying match details.
 *
 * @param modifier The modifier for the ScoreBoardRowComponent layout.
 * @param scoreType The type of the score (e.g., full time, half time).
 * @param firstTeamName The name of the first team.
 * @param secondTeamName The name of the second team.
 * @param firstTeamScore The score of the first team.
 * @param secondTeamScore The score of the second team.
 * @param firstTeamHalftimeScore The halftime score of the first team.
 * @param secondTeamHalftimeScore The halftime score of the second team.
 * @param isClickable Determines whether the row is clickable or not.
 * @param isFavourite Indicates whether the match is marked as a favorite.
 * @param onFavClick Callback for handling favorite icon clicks.
 * @param onClick Callback for handling clicks on the row.
 */
@Composable
fun ScoreBoardRowComponent(
    modifier: Modifier = Modifier,
    scoreType: String? = null,
    firstTeamName: String? = null,
    secondTeamName: String? = null,
    firstTeamScore: Int? = null,
    secondTeamScore: Int? = null,
    firstTeamHalftimeScore: Int? = null,
    secondTeamHalftimeScore: Int? = null,
    isClickable: Boolean = true,
    isFavourite: Boolean? = false,
    onFavClick: (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable(isClickable) {
                onClick?.invoke()
            }
    ) {
        val (type, firstTeam, secondTeam, fullTimeScore, favIcon, halfTimeScore) = createRefs()
        Text(
            text = scoreType ?: "",
            modifier = Modifier.constrainAs(type) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            color = MAIN_COLOR,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = firstTeamName ?: "",
            textAlign = TextAlign.End,
            modifier = Modifier.constrainAs(firstTeam) {
                start.linkTo(type.end, 8.dp)
                end.linkTo(fullTimeScore.start, 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            color = MAIN_COLOR
        )
        ResultComponent(
            modifier = Modifier.constrainAs(fullTimeScore) {
                end.linkTo(parent.end)
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            firstTeamScore = firstTeamScore,
            secondTeamScore = secondTeamScore
        )
        Text(
            text = secondTeamName ?: "",
            textAlign = TextAlign.Start,
            modifier = Modifier.constrainAs(secondTeam) {
                start.linkTo(fullTimeScore.end, 8.dp)
                end.linkTo(favIcon.start, 8.dp)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                width = Dimension.fillToConstraints
            },
            color = MAIN_COLOR
        )
        Image(
            painter = painterResource(id = if (isFavourite == true) R.drawable.ic_favourite_24dp else R.drawable.ic_favourite_outlined_24dp),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(favIcon) {
                    end.linkTo(halfTimeScore.start, 8.dp)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .clickable {
                    onFavClick?.invoke()
                }
        )
        ResultComponent(
            modifier = Modifier.constrainAs(halfTimeScore) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
            },
            firstTeamScore = firstTeamHalftimeScore,
            secondTeamScore = secondTeamHalftimeScore,
            backgroundColor = Color.Transparent,
            textColor = MAIN_COLOR,
            textStyle = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold)
        )
    }
}
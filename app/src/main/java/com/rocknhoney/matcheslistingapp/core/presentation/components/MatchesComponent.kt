package com.rocknhoney.matcheslistingapp.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity

/**
 * Composable function responsible for rendering a list of match items in the UI.
 *
 * @param modifier The modifier for the MatchesComponent layout.
 * @param contentList The list of MatchViewEntity objects representing matches to display.
 * @param isClickable Determines whether the match items are clickable or not.
 * @param onFavClick Callback for handling favorite icon clicks on match items.
 * @param onClick Callback for handling clicks on match items.
 */
@Composable
fun MatchesComponent(
    modifier: Modifier = Modifier,
    contentList: List<MatchViewEntity?>? = null,
    isClickable: Boolean = true,
    onFavClick: ((MatchViewEntity?) -> Unit)? = null,
    onClick: ((MatchViewEntity?) -> Unit)? = null
) {
    val rowCount: Int = contentList?.filterNotNull()?.size ?: 0
    Box(
        modifier = modifier
            .wrapContentHeight()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Column {
            contentList?.forEachIndexed { index, data ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    ScoreBoardRowComponent(
                        modifier = Modifier.align(Alignment.Center),
                        scoreType = data?.sc?.abbr,
                        firstTeamName = data?.ht?.sn,
                        secondTeamName = data?.at?.sn,
                        firstTeamScore = data?.sc?.ht?.r,
                        secondTeamScore = data?.sc?.at?.r,
                        firstTeamHalftimeScore = data?.sc?.ht?.ht,
                        secondTeamHalftimeScore = data?.sc?.at?.ht,
                        isClickable = isClickable,
                        isFavourite = data?.isFavourite,
                        onFavClick = { onFavClick?.invoke(data) },
                        onClick = { onClick?.invoke(data) }
                    )
                }
                if (index < rowCount - 1) {
                    Divider(
                        thickness = 1.dp,
                        color = Color.LightGray
                    )
                }
            }
        }
    }
}
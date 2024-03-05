package com.rocknhoney.matcheslistingapp.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.rocknhoney.matcheslistingapp.core.data.model.MatchViewEntity

/**
 * Composable function responsible for rendering a league component in the UI.
 *
 * @param modifier The modifier for the LeagueComponent layout.
 * @param contentList The list of MatchViewEntity objects representing matches in the league.
 * @param isClickable Determines whether the league component is clickable or not.
 * @param onFavClick Callback for handling favorite icon clicks on match items.
 * @param onClick Callback for handling clicks on match items.
 */
@Composable
fun LeagueComponent(
    modifier: Modifier = Modifier,
    contentList: List<MatchViewEntity?>? = null,
    isClickable: Boolean = true,
    onFavClick: ((MatchViewEntity?) -> Unit)? = null,
    onClick: ((MatchViewEntity?) -> Unit)? = null
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = contentList?.get(0)?.to?.flag,
                contentDescription = null,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(contentList?.get(0)?.to?.n ?: "", color = Color.White)
        }
        Spacer(modifier = Modifier.height(8.dp))
        MatchesComponent(
            contentList = contentList,
            isClickable = isClickable,
            onFavClick = {
                onFavClick?.invoke(it)
            },
            onClick = {
                onClick?.invoke(it)
            }
        )
    }
}
package com.rocknhoney.matcheslistingapp.features.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rocknhoney.matcheslistingapp.R
import com.rocknhoney.matcheslistingapp.core.presentation.components.MatchesComponent
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MAIN_COLOR
import com.rocknhoney.matcheslistingapp.core.util.toDateFormat

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    val match by viewModel.match.collectAsState()
    Column(
        Modifier
            .fillMaxSize()
            .background(MAIN_COLOR),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${match?.ht?.sn} - ${match?.at?.sn}",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        match?.d?.toDateFormat()?.let {
            Text(
                text = it,
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        MatchesComponent(
            modifier = Modifier.padding(horizontal = 8.dp),
            isClickable = false,
            contentList = listOf(match),
            onFavClick = {
                viewModel.onFavIconClicked(it)
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = (match?.ht?.rc ?: 0).toString(), color = Color.White)
            Image(
                painter = painterResource(id = R.drawable.il_red_card),
                contentDescription = null,
                modifier = Modifier.size(25.dp)
            )
            Text(text = (match?.at?.rc ?: 0).toString(), color = Color.White)
        }
    }
}
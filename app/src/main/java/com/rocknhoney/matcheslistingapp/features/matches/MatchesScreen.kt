package com.rocknhoney.matcheslistingapp.features.matches

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import com.rocknhoney.matcheslistingapp.R
import com.rocknhoney.matcheslistingapp.core.data.enums.ScreenState
import com.rocknhoney.matcheslistingapp.core.presentation.components.LeagueComponent
import com.rocknhoney.matcheslistingapp.core.presentation.components.LifecycleEventListenerComponent
import com.rocknhoney.matcheslistingapp.core.presentation.navigation.Screen
import com.rocknhoney.matcheslistingapp.core.presentation.theme.MAIN_COLOR
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MatchesScreen(navController: NavHostController, viewModel: MatchesViewModel = hiltViewModel()) {

    val matches by viewModel.matches.collectAsState()
    val screenState by viewModel.screenState.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .systemBarsPadding()
    ) {
        val pagerState = rememberPagerState(pageCount = { 2 })
        val coroutineScope = rememberCoroutineScope()

        val tabs = listOf(
            stringResource(id = R.string.matches),
            stringResource(id = R.string.favourites)
        )

        TabRow(
            selectedTabIndex = pagerState.currentPage,
            divider = {
                Spacer(modifier = Modifier.height(5.dp))
            },
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            containerColor = Color.White,
            contentColor = MAIN_COLOR
        ) {
            tabs.forEachIndexed { index, tabName ->
                Tab(selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(text = tabName)
                    }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            when (page) {
                0 -> {
                    when (screenState) {
                        ScreenState.LOADING, ScreenState.SUCCESS -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                            ) {
                                LazyColumn(
                                    Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(24.dp),
                                    contentPadding = PaddingValues(vertical = 16.dp)
                                ) {
                                    matches?.let { leagueList ->
                                        items(leagueList.size) { index ->
                                            LeagueComponent(contentList = leagueList[index],
                                                onClick = {
                                                    navController.navigate(
                                                        Screen.DetailScreen.withArgs(
                                                            viewModel.convertMatchToJson(it)
                                                        )
                                                    )
                                                },
                                                onFavClick = {
                                                    viewModel.onFavIconClicked(it)
                                                }
                                            )
                                        }
                                    }
                                }
                                if (screenState == ScreenState.LOADING) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.align(Alignment.Center),
                                        color = Color.White
                                    )
                                }
                            }
                        }

                        ScreenState.ERROR -> {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(MAIN_COLOR),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = stringResource(id = R.string.fav_match_not_found),
                                    modifier = Modifier
                                        .padding(16.dp),
                                    color = Color.White,
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(12.dp))
                                Button(onClick = {
                                    viewModel.getPosts()
                                }) {
                                    Text(text = stringResource(id = R.string.try_again))
                                }
                            }
                        }
                    }
                }

                1 -> {
                    val favouriteMatches =
                        matches?.flatMap { it.orEmpty() }?.filter { it?.isFavourite == true }
                    if (favouriteMatches?.isNotEmpty() == true) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            LazyColumn(
                                Modifier.fillMaxWidth(),
                                verticalArrangement = Arrangement.spacedBy(24.dp),
                                contentPadding = PaddingValues(vertical = 16.dp)
                            ) {
                                favouriteMatches.groupBy { it?.to?.i }.values.toList()
                                    .let { leagueList ->
                                        items(leagueList.size) { index ->
                                            LeagueComponent(contentList = leagueList[index],
                                                onClick = {
                                                    navController.navigate(
                                                        Screen.DetailScreen.withArgs(
                                                            viewModel.convertMatchToJson(it)
                                                        )
                                                    )
                                                },
                                                onFavClick = {
                                                    viewModel.onFavIconClicked(it)
                                                }
                                            )
                                        }
                                    }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MAIN_COLOR)
                        ) {
                            Text(
                                text = stringResource(id = R.string.fav_match_not_found),
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(16.dp),
                                color = Color.White,
                                style = MaterialTheme.typography.headlineSmall,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
        LifecycleEventListenerComponent { owner, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.refreshList()
            }
        }
    }
}
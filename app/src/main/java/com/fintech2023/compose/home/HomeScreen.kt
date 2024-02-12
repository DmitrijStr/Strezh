package com.fintech2023.compose.home

import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.fintech2023.R
import com.fintech2023.compose.popularlist.PopularScreen
import com.fintech2023.data.Film
import com.fintech2023.ui.theme.FilmsTheme
import kotlinx.coroutines.launch

enum class FilmPage(
    @StringRes val titleResId: Int,
) {
    POPULAR_LIST(R.string.popular_films_title),
    FAVORITES(R.string.fav_films_title)
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onFilmClick: (Film) -> Unit = {},
    modifier: Modifier = Modifier,
    pages: Array<FilmPage> = FilmPage.values()

) {
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        topBar = {
            HomeTopAppBar(
                pagerState = pagerState,
                scrollBehavior = scrollBehavior
            )
        }
    ) { padding ->

        HomePagerScreen(
            onFilmClick = onFilmClick,
            pagerState = pagerState,
            pages = pages,
            modifier = modifier.padding(top = padding.calculateTopPadding())
        )
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomePagerScreen(
    onFilmClick: (Film) -> Unit,
    pagerState: PagerState,
    pages: Array<FilmPage>,
    modifier: Modifier = Modifier
) {

    Column(modifier = modifier.fillMaxHeight()) {
        val coroutineScope = rememberCoroutineScope()

        TabRow(
            selectedTabIndex = pagerState.currentPage,
        ) {

            pages.forEachIndexed { index, page ->
                val title = stringResource(id = page.titleResId)
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(index) } },
                    text = { Text(text = title) }

                )
            }
        }

        HorizontalPager(
            state = pagerState,
        ) { idx ->
            when (pages[idx]) {
                FilmPage.POPULAR_LIST -> {
                    PopularScreen(
                        onFilmClick = onFilmClick
                    )
                }

                FilmPage.FAVORITES -> {

                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    pagerState: PagerState,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(id = FilmPage.entries[pagerState.currentPage].titleResId),
                style = MaterialTheme.typography.displaySmall
            )

        },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}


@Preview
@Composable
private fun HomeScreenPreview() {

    FilmsTheme {
        HomeScreen()
    }
}
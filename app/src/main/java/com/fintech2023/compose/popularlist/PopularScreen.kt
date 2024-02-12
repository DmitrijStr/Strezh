package com.fintech2023.compose.popularlist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.fintech2023.data.Film
import com.fintech2023.viewmodels.PopularListViewModel
import kotlinx.coroutines.flow.Flow
import com.fintech2023.R

@Composable
fun PopularScreen(
    onFilmClick: (Film) -> Unit,
    viewModel: PopularListViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    Scaffold { padding ->
        FilmsList(
            films = viewModel.films,
            onFilmClick = onFilmClick,
            modifier = Modifier.padding(padding),
            onLongFilmClick = { film -> viewModel.saveFilmInfo(film) }
        )
    }
}

@Composable
fun FilmsList(
    films: Flow<PagingData<Film>>,
    onFilmClick: (Film) -> Unit,
    modifier: Modifier = Modifier,
    onLongFilmClick: (Film) -> Unit = {},
) {
    val pagingFilms: LazyPagingItems<Film> = films.collectAsLazyPagingItems()

    if (pagingFilms.loadState.refresh == LoadState.Loading) {
        Text(text = stringResource(id = R.string.loading_progress))

        return
    }

    if (pagingFilms.loadState.hasError) {
        Text(text = stringResource(id = R.string.loading_error))

        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        items(
            count = pagingFilms.itemCount,
            key = pagingFilms.itemKey { it.filmId }
        ) { index ->
            val film = pagingFilms[index] ?: return@items

            FilmListItem(
                film = film,
                onClick = { onFilmClick(film) },
                onLongClick = { onLongFilmClick(film) }
            )
        }
    }
}
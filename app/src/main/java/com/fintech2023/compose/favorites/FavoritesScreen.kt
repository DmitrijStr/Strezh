package com.fintech2023.compose.favorites

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.fintech2023.compose.popularlist.FilmListItem
import com.fintech2023.data.Film
import com.fintech2023.data.FilmInfo
import com.fintech2023.data.Genre
import com.fintech2023.viewmodels.FavoritesViewModel

@Composable
fun FavoritesScreen(
    onClick: (Film) -> Unit,
    viemodel: FavoritesViewModel = hiltViewModel(),
) {

    val localFilms by viemodel.localFilms.collectAsState(initial = emptyList())

    SavedFilmsList(localFilms, onClick)
}

@Composable
fun SavedFilmsList(
    films: List<FilmInfo>,
    onClick: (Film) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(1)
    ) {
        items(films) { it ->

            val film = Film(
                it.filmId,
                it.name,
                releaseYear = it.releaseYear,
                genres = listOf(Genre(it.genre)),
                posterUrlPreview = it.posterUrlPreview
            )

            FilmListItem(
                film = film, onClick = { onClick(film) }
            )
        }
    }

}
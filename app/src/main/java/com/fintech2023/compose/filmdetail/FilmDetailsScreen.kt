package com.fintech2023.compose.filmdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fintech2023.R
import com.fintech2023.data.Country
import com.fintech2023.data.FilmDetailResponse
import com.fintech2023.data.Genre
import com.fintech2023.viewmodels.FilmDetailViewModel

@Composable
fun FilmDetailsScreen(
    filmDetailViewModel: FilmDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit,

    ) {
    val film = filmDetailViewModel.film.observeAsState().value

    if (film != null) {
        FilmDetail(film)
    }
}

@Composable
fun FilmDetail(
    film: FilmDetailResponse,
) {

    val scrollState = rememberScrollState()

    Column(
        Modifier.verticalScroll(scrollState)
    ) {
        FilmImage(film.posterUrl)
        FilmInfo(
            name = film.name,
            description = film.description,
            genre = film.genres,
            country = film.countries
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun FilmImage(
    imageUrl: String,
    modifier: Modifier = Modifier
) {
    GlideImage(
        model = imageUrl, contentDescription = null,
        modifier = modifier
            .fillMaxWidth(),
//            .height(dimensionResource(id = R.dimen.card_heigh1t)),
        contentScale = ContentScale.FillWidth
    )
}

@Composable
fun FilmInfo(
    name: String,
    description: String,
    genre: List<Genre>,
    country: List<Country>,
) {

    Column(modifier = Modifier.padding(horizontal = 30.dp)) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(vertical = 15.dp)
        )
        Text(text = description)

        Row(
            modifier = Modifier.padding(vertical = 15.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.genres) + " ")
            Text(text = genre.joinToString(", "))
        }
        Row(
            modifier = Modifier.padding(bottom = 30.dp)
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                text = stringResource(id = R.string.countries) + " ")
            Text(text = country.joinToString(", "))
        }
    }

}



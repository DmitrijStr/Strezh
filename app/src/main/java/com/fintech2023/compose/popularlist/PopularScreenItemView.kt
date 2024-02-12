package com.fintech2023.compose.popularlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fintech2023.R
import com.fintech2023.data.Film


@OptIn(
    ExperimentalGlideComposeApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun FilmListItem(
    film: Film,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {}
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(0.dp, Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .height(dimensionResource(id = R.dimen.card_height))
            .padding(horizontal = dimensionResource(id = R.dimen.card_side_margin))
            .padding(bottom = dimensionResource(id = R.dimen.card_bottom_margin))
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            )

    ) {
        Row {
            GlideImage(
                model = film.posterUrlPreview,
                contentDescription = stringResource(R.string.film_detail_image_content_description),
                Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp)
                    .width(dimensionResource(id = R.dimen.film_item_image_width)),
            )
            Column(
                modifier = Modifier.padding(horizontal = dimensionResource(id = R.dimen.film_item_image_width))
            ) {
                Text(
                    fontSize = 16.sp,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontWeight = FontWeight.SemiBold,
                    text = film.name ?: "",
                    modifier = Modifier.padding(top = 15.dp)
                )
                Text(text = "${film.genres[0].genre} (${film.releaseYear})")
            }
//            Text(text = "FAV")
        }
    }
}
package com.fintech2023.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.fintech2023.api.FilmService

private const val TOP_FILMS_STARTING_PAGE_INDEX = 1


class FilmPagingSource(
    private val service: FilmService,
    private val query: String? = null,
) : PagingSource<Int, Film>() {
    override fun getRefreshKey(state: PagingState<Int, Film>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Film> {

        val pageNumber = params.key ?: TOP_FILMS_STARTING_PAGE_INDEX

        return try {
            val res = service.topPhotos(pageNumber)
            val films = res.results

            Log.d("SSS", "films: ${films}")

            LoadResult.Page(
                data = films,
                prevKey = if (pageNumber == TOP_FILMS_STARTING_PAGE_INDEX) null else pageNumber - 1,
                nextKey = if (pageNumber == res.totalPages) null else pageNumber + 1
            )


        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
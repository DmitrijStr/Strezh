package com.fintech2023.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fintech2023.api.FilmService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FilmRepository @Inject constructor(private val service: FilmService) {

    fun getFilmsStream(): Flow<PagingData<Film>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = NETWORK_PAGE_SIZE),
            pagingSourceFactory = { FilmPagingSource(service) }
        ).flow
    }

    suspend fun getFilmInfo(id: String): FilmDetailResponse {
        return service.filmDetails(id)
    }




    companion object {
        private const val NETWORK_PAGE_SIZE = 20
    }
}
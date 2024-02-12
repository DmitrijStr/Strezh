package com.fintech2023.data

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalFilmRepository @Inject constructor(
    private val filmDao: FilmDao
) {

    suspend fun createFilm(film: Film) {
        val filmInfo = FilmInfo(
            filmId = film.filmId,
            name = film.name,
            posterUrlPreview = film.posterUrlPreview,
            genre = film.genres[0].genre,
            releaseYear = film.releaseYear
        )

        filmDao.insertFilm(filmInfo)
    }

    fun getLocalFilms() = filmDao.getFilms()
}
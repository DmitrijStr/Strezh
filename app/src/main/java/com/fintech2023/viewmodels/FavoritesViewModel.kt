package com.fintech2023.viewmodels

import androidx.lifecycle.ViewModel
import com.fintech2023.data.LocalFilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val localFilmRepository: LocalFilmRepository
) : ViewModel() {
    val localFilms = localFilmRepository.getLocalFilms()
}
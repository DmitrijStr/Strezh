package com.fintech2023.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fintech2023.data.Film
import com.fintech2023.data.FilmRepository
import com.fintech2023.data.LocalFilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(
    private val repository: FilmRepository,
    private val localFilmRepository: LocalFilmRepository
) : ViewModel() {

    private val _films = MutableStateFlow<PagingData<Film>?>(null)
    val films: Flow<PagingData<Film>> get() = _films.filterNotNull()

    init {
        getData()
    }

    fun saveFilmInfo(film: Film) {
        viewModelScope.launch {
            localFilmRepository.createFilm(film)
        }
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                _films.value = repository.getFilmsStream().cachedIn(viewModelScope).first()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
package com.fintech2023.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fintech2023.data.FilmDetailResponse
import com.fintech2023.data.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FilmDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: FilmRepository
) : ViewModel() {
    private val filmId: String = savedStateHandle.get<String>("filmId")!!

    private val _film = MutableStateFlow<FilmDetailResponse?>(null)
    val film: LiveData<FilmDetailResponse?> get() = _film.asLiveData()

    init {
        getFilm(filmId)
    }

    private fun getFilm(id: String) {
        viewModelScope.launch {
            try {
                _film.value = repository.getFilmInfo(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
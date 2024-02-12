package com.fintech2023.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.fintech2023.data.Film
import com.fintech2023.data.FilmRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularListViewModel @Inject constructor(
    private val repository: FilmRepository
) : ViewModel() {

    private val _films = MutableStateFlow<PagingData<Film>?>(null)
    val films: Flow<PagingData<Film>> get() = _films.filterNotNull()

    init {
        getData()
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
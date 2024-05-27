package com.example.lenguajeseas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lenguajeseas.model.Semana
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SemanasViewModel : ViewModel() {
    private val _favoriteSemanas = MutableStateFlow<List<Semana>>(emptyList())
    val favoriteSemanas: StateFlow<List<Semana>> get() = _favoriteSemanas

    fun addFavorite(semana: Semana) {
        viewModelScope.launch {
            val currentFavorites = _favoriteSemanas.value.toMutableList()
            if (!currentFavorites.contains(semana)) {
                currentFavorites.add(semana)
                _favoriteSemanas.value = currentFavorites
            }
        }
    }

    fun removeFavorite(semana: Semana) {
        viewModelScope.launch {
            val currentFavorites = _favoriteSemanas.value.toMutableList()
            if (currentFavorites.contains(semana)) {
                currentFavorites.remove(semana)
                _favoriteSemanas.value = currentFavorites
            }
        }
    }

    fun isFavorite(semana: Semana): Boolean {
        return _favoriteSemanas.value.contains(semana)
    }
}

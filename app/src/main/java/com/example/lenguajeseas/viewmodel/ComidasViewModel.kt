package com.example.lenguajeseas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lenguajeseas.model.Comida
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ComidasViewModel : ViewModel() {
    private val _favoriteComidas = MutableStateFlow<List<Comida>>(emptyList())
    val favoriteComidas: StateFlow<List<Comida>> get() = _favoriteComidas

    fun addFavorite(comida: Comida) {
        viewModelScope.launch {
            val currentFavorites = _favoriteComidas.value.toMutableList()
            if (!currentFavorites.contains(comida)) {
                currentFavorites.add(comida)
                _favoriteComidas.value = currentFavorites
            }
        }
    }

    fun removeFavorite(comida: Comida) {
        viewModelScope.launch {
            val currentFavorites = _favoriteComidas.value.toMutableList()
            if (currentFavorites.contains(comida)) {
                currentFavorites.remove(comida)
                _favoriteComidas.value = currentFavorites
            }
        }
    }

    fun isFavorite(comida: Comida): Boolean {
        return _favoriteComidas.value.contains(comida)
    }
}

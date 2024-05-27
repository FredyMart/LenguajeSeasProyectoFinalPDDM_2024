package com.example.lenguajeseas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private val _favorites = MutableStateFlow<List<Int>>(emptyList())
    val favorites: StateFlow<List<Int>> get() = _favorites

    fun addFavorite(resourceId: Int) {
        viewModelScope.launch {
            _favorites.value = _favorites.value + resourceId
        }
    }

    fun removeFavorite(resourceId: Int) {
        viewModelScope.launch {
            _favorites.value = _favorites.value - resourceId
        }
    }

    fun isFavorite(resourceId: Int): Boolean {
        return _favorites.value.contains(resourceId)
    }
}

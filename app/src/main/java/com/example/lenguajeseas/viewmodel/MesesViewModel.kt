package com.example.lenguajeseas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lenguajeseas.model.Mes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MesesViewModel : ViewModel() {
    private val _favoriteMeses = MutableStateFlow<List<Mes>>(emptyList())
    val favoriteMeses: StateFlow<List<Mes>> get() = _favoriteMeses

    fun addFavorite(mes: Mes) {
        viewModelScope.launch {
            val currentFavorites = _favoriteMeses.value.toMutableList()
            if (!currentFavorites.contains(mes)) {
                currentFavorites.add(mes)
                _favoriteMeses.value = currentFavorites
            }
        }
    }

    fun removeFavorite(mes: Mes) {
        viewModelScope.launch {
            val currentFavorites = _favoriteMeses.value.toMutableList()
            if (currentFavorites.contains(mes)) {
                currentFavorites.remove(mes)
                _favoriteMeses.value = currentFavorites
            }
        }
    }

    fun isFavorite(mes: Mes): Boolean {
        return _favoriteMeses.value.contains(mes)
    }
}

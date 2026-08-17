package com.themify.app.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.themify.app.data.model.Theme
import com.themify.app.data.repository.ThemeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ThemeUiState(
    val themes: List<Theme> = emptyList(),
    val filteredThemes: List<Theme> = emptyList(),
    val selectedCategory: String = "All",
    val searchQuery: String = "",
    val appliedThemeId: String? = null,
    val isLoading: Boolean = false
)

class ThemeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ThemeRepository(application)
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState.asStateFlow()

    init { loadThemes() }

    private fun loadThemes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val themes = repository.getThemes()
            _uiState.update { it.copy(themes = themes, filteredThemes = themes, isLoading = false) }
        }
    }

    fun selectCategory(category: String) {
        _uiState.update { state ->
            state.copy(
                selectedCategory = category,
                filteredThemes = filter(state.themes, category, state.searchQuery)
            )
        }
    }

    fun search(query: String) {
        _uiState.update { state ->
            state.copy(
                searchQuery = query,
                filteredThemes = filter(state.themes, state.selectedCategory, query)
            )
        }
    }

    fun applyTheme(themeId: String) {
        _uiState.update { it.copy(appliedThemeId = themeId) }
    }

    private fun filter(themes: List<Theme>, category: String, query: String) =
        themes
            .filter { if (category == "All") true else it.category == category }
            .filter {
                if (query.isBlank()) true
                else it.name.lowercase().contains(query.lowercase()) ||
                     it.tags.any { t -> t.lowercase().contains(query.lowercase()) }
            }
}

package com.themify.app.ui.browse

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.themify.app.data.model.CATEGORIES
import com.themify.app.ui.components.ThemeCard
import com.themify.app.viewmodel.ThemeUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrowseScreen(
    uiState: ThemeUiState,
    onCategorySelected: (String) -> Unit,
    onSearch: (String) -> Unit,
    onThemeClick: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Themify", style = MaterialTheme.typography.headlineMedium) },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
        )
        OutlinedTextField(
            value = uiState.searchQuery,
            onValueChange = onSearch,
            placeholder = { Text("Search themes...") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
            shape = MaterialTheme.shapes.large
        )
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            items(CATEGORIES) { category ->
                FilterChip(
                    selected = uiState.selectedCategory == category,
                    onClick = { onCategorySelected(category) },
                    label = { Text(category) }
                )
            }
        }
        Spacer(Modifier.height(8.dp))
        when {
            uiState.isLoading -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                CircularProgressIndicator()
            }
            uiState.filteredThemes.isEmpty() -> Box(Modifier.fillMaxSize(), Alignment.Center) {
                Text("No themes found", color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
            }
            else -> LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.filteredThemes, key = { it.id }) { theme ->
                    ThemeCard(
                        theme = theme,
                        isApplied = uiState.appliedThemeId == theme.id,
                        onClick = { onThemeClick(theme.id) }
                    )
                }
            }
        }
    }
}

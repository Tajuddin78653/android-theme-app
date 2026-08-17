package com.themify.app.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.themify.app.ui.browse.BrowseScreen
import com.themify.app.ui.preview.PreviewScreen
import com.themify.app.viewmodel.ThemeViewModel

sealed class Screen(val route: String) {
    object Browse  : Screen("browse")
    object Preview : Screen("preview/{themeId}") {
        fun createRoute(id: String) = "preview/$id"
    }
}

@Composable
fun ThemifyNavGraph() {
    val navController = rememberNavController()
    val viewModel: ThemeViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(navController, startDestination = Screen.Browse.route) {
        composable(Screen.Browse.route) {
            BrowseScreen(
                uiState            = uiState,
                onCategorySelected = viewModel::selectCategory,
                onSearch           = viewModel::search,
                onThemeClick       = { navController.navigate(Screen.Preview.createRoute(it)) }
            )
        }
        composable(
            Screen.Preview.route,
            listOf(navArgument("themeId") { type = NavType.StringType })
        ) { back ->
            val id    = back.arguments?.getString("themeId") ?: return@composable
            val theme = uiState.themes.find { it.id == id }   ?: return@composable
            PreviewScreen(
                theme     = theme,
                isApplied = uiState.appliedThemeId == id,
                onBack    = { navController.popBackStack() },
                onApply   = { viewModel.applyTheme(id) }
            )
        }
    }
}

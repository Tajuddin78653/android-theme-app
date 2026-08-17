package com.themify.app.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.themify.app.data.model.Theme

class ThemeRepository(private val context: Context) {

    private val gson = Gson()

    fun getThemes(): List<Theme> {
        return try {
            val json = context.assets.open("themes.json")
                .bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Theme>>() {}.type
            gson.fromJson(json, type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun getThemeById(id: String): Theme? = getThemes().find { it.id == id }

    fun getThemesByCategory(category: String): List<Theme> =
        if (category == "All") getThemes()
        else getThemes().filter { it.category == category }

    fun searchThemes(query: String): List<Theme> {
        val lower = query.lowercase()
        return getThemes().filter {
            it.name.lowercase().contains(lower) ||
            it.category.lowercase().contains(lower) ||
            it.tags.any { tag -> tag.lowercase().contains(lower) }
        }
    }
}

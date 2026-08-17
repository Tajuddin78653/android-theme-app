package com.themify.app.data.model

data class Theme(
    val id: String,
    val name: String,
    val category: String,
    val author: String,
    val primaryColor: String,
    val secondaryColor: String,
    val accentColor: String,
    val backgroundColor: String,
    val surfaceColor: String,
    val textColor: String,
    val fontFamily: String,
    val thumbnailUrl: String,
    val wallpaperUrl: String,
    val tags: List<String>,
    val downloads: Int,
    val rating: Float
)

val CATEGORIES = listOf("All", "Dark", "Light", "Minimal", "Colorful", "Nature")

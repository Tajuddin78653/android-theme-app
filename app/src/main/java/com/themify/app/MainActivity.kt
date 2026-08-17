package com.themify.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.themify.app.navigation.ThemifyNavGraph
import com.themify.app.ui.theme.ThemifyAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ThemifyAppTheme {
                ThemifyNavGraph()
            }
        }
    }
}

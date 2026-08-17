package com.themify.app.ui.preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.themify.app.data.model.Theme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PreviewScreen(
    theme: Theme,
    isApplied: Boolean,
    onBack: () -> Unit,
    onApply: () -> Unit
) {
    val bgColor   = Color(android.graphics.Color.parseColor(theme.backgroundColor))
    val primary   = Color(android.graphics.Color.parseColor(theme.primaryColor))
    val secondary = Color(android.graphics.Color.parseColor(theme.secondaryColor))
    val accent    = Color(android.graphics.Color.parseColor(theme.accentColor))
    val textCol   = Color(android.graphics.Color.parseColor(theme.textColor))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(theme.name) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(shadowElevation = 8.dp) {
                Button(
                    onClick = onApply,
                    modifier = Modifier.fillMaxWidth().padding(16.dp).height(52.dp),
                    enabled = !isApplied,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        if (isApplied) "\u2713 Applied" else "Apply Theme",
                        fontSize = 16.sp, fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().height(220.dp).background(bgColor),
                contentAlignment = Alignment.Center
            ) {
                Text(theme.name, color = textCol, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text("By ${theme.author}", style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                Spacer(Modifier.height(4.dp))
                Text("${theme.downloads} downloads  \u2022  \u2605 ${theme.rating}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f))
                Spacer(Modifier.height(20.dp))
                Text("Colour Palette", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(12.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    listOf("Primary" to primary, "Secondary" to secondary, "Accent" to accent, "BG" to bgColor)
                        .forEach { (label, color) ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(Modifier.size(48.dp).clip(CircleShape).background(color))
                                Spacer(Modifier.height(4.dp))
                                Text(label, style = MaterialTheme.typography.labelSmall,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f))
                            }
                        }
                }
                Spacer(Modifier.height(20.dp))
                Text("Font", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Text(theme.fontFamily, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                Text("The quick brown fox jumps over the lazy dog",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f))
                Spacer(Modifier.height(20.dp))
                Text("Tags", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    theme.tags.forEach { tag -> AssistChip(onClick = {}, label = { Text("#$tag") }) }
                }
            }
        }
    }
}

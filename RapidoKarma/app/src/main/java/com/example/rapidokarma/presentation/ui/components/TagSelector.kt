package com.example.rapidokarma.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.rapidokarma.presentation.ui.theme.RapidoBlack
import com.example.rapidokarma.presentation.ui.theme.RapidoYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagSelector(selectedTag: String, onTagSelected: (String) -> Unit) {
    val tags = listOf(
        "Driver was polite" to "👋",
        "Reached on time" to "⏰",
        "Safe driving" to "🛡️",
        "Clean vehicle" to "✨",
        "Helpful" to "🤝",
        "Good navigation" to "🗺️"
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            "What went well?",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = RapidoBlack
            ),
            modifier = Modifier.padding(bottom = 12.dp)
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            tags.forEach { (tag, emoji) ->
                val isSelected = tag == selectedTag
                FilterChip(
                    selected = isSelected,
                    onClick = { onTagSelected(tag) },
                    label = {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(emoji)
                            Text(
                                tag,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                )
                            )
                        }
                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = RapidoYellow,
                        selectedLabelColor = RapidoBlack
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        borderColor = Color.LightGray,
                        selectedBorderColor = RapidoYellow,
                        enabled = isSelected,
                        selected = isSelected
                    )
                )
            }
        }
    }
}

package com.example.rapidokarma.presentation.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rapidokarma.presentation.ui.theme.RapidoBlack
import com.example.rapidokarma.presentation.ui.theme.RapidoYellow

@Composable
@Preview
fun EmojiSelector(selectedEmoji: String = "😐", onEmojiSelected: (String) -> Unit = {}) {
    val emojis = listOf(
        Pair("😄", "Excellent"),
        Pair("🙂", "Good"),
        Pair("😐", "Okay"),
        Pair("🙁", "Bad"),
        Pair("😡", "Very Bad"),
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "How was your ride?",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
                color = RapidoBlack
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            emojis.forEach { (emoji, label) ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(horizontal = 4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(
                                if (emoji == selectedEmoji) RapidoYellow
                                else Color.LightGray.copy(alpha = 0.3f)
                            )
                            .clickable { onEmojiSelected(emoji) }
                            .animateContentSize(
                                animationSpec = spring(
                                    dampingRatio = Spring.DampingRatioMediumBouncy,
                                    stiffness = Spring.StiffnessLow
                                )
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = emoji,
                            fontSize = if (emoji == selectedEmoji) 32.sp else 24.sp
                        )
                    }
                    
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = if (emoji == selectedEmoji) RapidoBlack else Color.Gray,
                            fontWeight = if (emoji == selectedEmoji) FontWeight.Bold else FontWeight.Normal
                        ),
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }
    }
}

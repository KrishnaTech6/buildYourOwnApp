package com.example.rapidokarma.presentation.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rapidokarma.presentation.ui.theme.RapidoBlack
import com.example.rapidokarma.presentation.ui.theme.RapidoYellow

@Composable
@Preview(showBackground = true)
fun TipSelector(
    selectedAmount: Int = 10,
    onAmountSelected: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Appreciate your captain with a tip",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            color = RapidoBlack
        )
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            listOf(10, 20, 30, 50).forEach { amount ->
                val isSelected = amount == selectedAmount
                
                ElevatedCard(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp)
                        .height(80.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = if (isSelected) RapidoYellow else MaterialTheme.colorScheme.surface,
                        contentColor = if (isSelected) RapidoBlack else RapidoBlack.copy(alpha = 0.7f)
                    ),
                    elevation = CardDefaults.elevatedCardElevation(
                        defaultElevation = if (isSelected) 4.dp else 1.dp,
                        pressedElevation = 8.dp,
                        hoveredElevation = 4.dp,
                        focusedElevation = 4.dp
                    ),
                    onClick = { onAmountSelected(amount) }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape),
                            color = if (isSelected) RapidoBlack else RapidoYellow,
                            contentColor = if (isSelected) RapidoYellow else RapidoBlack
                        ) {
                            Icon(
                                imageVector = Icons.Default.MonetizationOn,
                                contentDescription = "Tip amount",
                                modifier = Modifier.padding(6.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "₹$amount",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                            ),
                            color = if (isSelected) RapidoBlack else RapidoBlack.copy(alpha = 0.7f),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

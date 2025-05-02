package com.example.rapidokarma.presentation.ui.feedback

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.rapidokarma.presentation.event.FeedbackEvent
import com.example.rapidokarma.presentation.state.FeedbackState
import com.example.rapidokarma.presentation.ui.components.EmojiSelector
import com.example.rapidokarma.presentation.ui.components.TagSelector
import com.example.rapidokarma.presentation.ui.components.TipSelector
import com.example.rapidokarma.presentation.ui.theme.RapidoBlack
import com.example.rapidokarma.presentation.ui.theme.RapidoGray
import com.example.rapidokarma.presentation.ui.theme.RapidoYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    state: FeedbackState = remember { FeedbackState() },
    onEvent: (FeedbackEvent) -> Unit = {},
    onFeedbackSubmitted: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSuccess, state.errorMessage) {
        if (state.isSuccess) {
            onFeedbackSubmitted()
        } else if (state.errorMessage != null) {
            snackbarHostState.showSnackbar(state.errorMessage)
        }
    }

    Scaffold(
        snackbarHost = { 
            SnackbarHost(snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    shape = RoundedCornerShape(8.dp),
                    containerColor = RapidoBlack,
                    contentColor = RapidoYellow,
                    action = {
                        TextButton(
                            onClick = { data.dismiss() },
                            colors = ButtonDefaults.textButtonColors(contentColor = RapidoYellow)
                        ) {
                            Text("Dismiss")
                        }
                    }
                ) {
                    Text(data.visuals.message)
                }
            }
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(RapidoGray)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Ride Details Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Driver Photo
                    AsyncImage(
                        model = state.driverPhoto,
                        contentDescription = "Driver Photo",
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    
                    // Ride Details
                    Text(
                        text = "How was your ride with ${state.driverName}?",
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = RapidoBlack,
                        textAlign = TextAlign.Center
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = state.pickupLocation,
                            style = MaterialTheme.typography.bodyMedium,
                            color = RapidoBlack.copy(alpha = 0.7f)
                        )
                        Text(
                            text = " → ",
                            style = MaterialTheme.typography.bodyMedium,
                            color = RapidoBlack.copy(alpha = 0.7f)
                        )
                        Text(
                            text = state.dropLocation,
                            style = MaterialTheme.typography.bodyMedium,
                            color = RapidoBlack.copy(alpha = 0.7f)
                        )
                    }
                    
                    Text(
                        text = "Ride ID: ${state.rideId}",
                        style = MaterialTheme.typography.bodySmall,
                        color = RapidoBlack.copy(alpha = 0.5f)
                    )
                }
            }

            // Feedback Card
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    Text(
                        "Rate your experience",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = RapidoBlack
                    )

                    EmojiSelector(state.emoji) { onEvent(FeedbackEvent.EmojiChanged(it)) }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp),
                        color = RapidoGray
                    )

                    Text(
                        "What could be improved?",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = RapidoBlack
                    )

                    TagSelector(state.tag) { onEvent(FeedbackEvent.TagChanged(it)) }

                    AnimatedVisibility(
                        visible = state.emoji.isNotEmpty() || state.tag.isNotEmpty(),
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        OutlinedTextField(
                            value = state.comment,
                            onValueChange = { onEvent(FeedbackEvent.CommentChanged(it)) },
                            label = { Text("Tell us more about your experience (optional)") },
                            modifier = Modifier.fillMaxWidth(),
                            minLines = 3,
                            maxLines = 5,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RapidoYellow,
                                unfocusedBorderColor = Color.LightGray,
                                focusedLabelColor = RapidoBlack,
                                unfocusedLabelColor = RapidoBlack.copy(alpha = 0.6f)
                            )
                        )
                    }

                    AnimatedVisibility(
                        visible = state.emoji in listOf("🙂", "😄") || state.tag.isNotEmpty(),
                        enter = fadeIn() + slideInVertically(),
                        exit = fadeOut() + slideOutVertically()
                    ) {
                        // Tip Section
                        TipSelector(
                            selectedAmount = state.tipAmount,
                            onAmountSelected = { onEvent(FeedbackEvent.TipAmountChanged(it)) }
                        )
                    }
                }
            }

            // Submit Button
            Button(
                onClick = { onEvent(FeedbackEvent.SubmitFeedback) },
                enabled = !state.isSubmitting && (state.emoji.isNotEmpty() || state.tag.isNotEmpty()),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RapidoYellow,
                    contentColor = RapidoBlack,
                    disabledContainerColor = RapidoGray,
                    disabledContentColor = RapidoBlack.copy(alpha = 0.3f)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 2.dp,
                    pressedElevation = 4.dp,
                    disabledElevation = 0.dp
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (state.isSubmitting) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = RapidoBlack
                    )
                } else {
                    Text(
                        "Submit Feedback",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.rapidokarma.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rapidokarma.presentation.event.FeedbackEvent
import com.example.rapidokarma.presentation.state.FeedbackState
import com.example.rapidokarma.presentation.ui.components.EmojiSelector
import com.example.rapidokarma.presentation.ui.components.TagSelector
import com.example.rapidokarma.presentation.ui.theme.RapidoBlack
import com.example.rapidokarma.presentation.ui.theme.RapidoGray
import com.example.rapidokarma.presentation.ui.theme.RapidoYellow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun FeedbackScreen(
    modifier: Modifier = Modifier,
    state: FeedbackState = remember { FeedbackState() },
    onEvent: (FeedbackEvent) -> Unit ={},
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.isSuccess, state.errorMessage) {
        if (state.isSuccess) {
           snackbarHostState.showSnackbar("Thank you for your feedback!")
        } else if (state.errorMessage != null) {
            snackbarHostState.showSnackbar(state.errorMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState)},
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Rate Your Ride",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = RapidoYellow,
                    titleContentColor = RapidoBlack
                )
            )
        },
        modifier = modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(RapidoGray)
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    EmojiSelector(state.emoji) { onEvent(FeedbackEvent.EmojiChanged(it)) }

                    HorizontalDivider()

                    TagSelector(state.tag) { onEvent(FeedbackEvent.TagChanged(it)) }

                    AnimatedVisibility(visible = state.emoji.isNotEmpty() || state.tag.isNotEmpty()) {
                        OutlinedTextField(
                            value = state.comment,
                            onValueChange = { onEvent(FeedbackEvent.CommentChanged(it)) },
                            label = { Text("Tell us more about your experience (optional)") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3,
                            shape = RoundedCornerShape(12.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = RapidoYellow,
                                unfocusedBorderColor = Color.LightGray
                            )
                        )
                    }
                }
            }

            Button(
                onClick = { onEvent(FeedbackEvent.SubmitFeedback) },
                enabled = !state.isSubmitting && (state.emoji.isNotEmpty() || state.tag.isNotEmpty()),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RapidoYellow,
                    contentColor = RapidoBlack,
                    disabledContainerColor = Color.LightGray
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
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        }
    }
}

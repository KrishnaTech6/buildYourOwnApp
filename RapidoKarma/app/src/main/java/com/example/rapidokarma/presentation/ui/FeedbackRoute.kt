package com.example.rapidokarma.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.example.rapidokarma.presentation.viewmodel.FeedbackViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue

@Composable
fun FeedbackRoute(viewModel: FeedbackViewModel = koinViewModel<FeedbackViewModel>()) {
    val state by viewModel.state.collectAsState()

    FeedbackScreen(
        state = state,
        onEvent = { viewModel.onEvent(it) }
    )
}
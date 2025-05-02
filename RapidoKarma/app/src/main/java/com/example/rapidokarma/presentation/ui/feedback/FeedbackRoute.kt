package com.example.rapidokarma.presentation.ui.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import org.koin.androidx.compose.koinViewModel
import com.example.rapidokarma.presentation.viewmodel.FeedbackViewModel

@Composable
fun FeedbackRoute(
    viewModel: FeedbackViewModel = koinViewModel(),
    onFeedbackSubmitted: () -> Unit = {}
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.isSuccess) {
        if (state.isSuccess) {
            onFeedbackSubmitted()
        }
    }

    FeedbackScreen(
        state = state,
        onEvent = viewModel::onEvent
    )
}
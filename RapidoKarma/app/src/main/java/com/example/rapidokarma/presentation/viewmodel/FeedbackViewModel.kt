package com.example.rapidokarma.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rapidokarma.domain.model.Feedback
import com.example.rapidokarma.domain.usecase.InsertFeedbackUseCase
import com.example.rapidokarma.presentation.event.FeedbackEvent
import com.example.rapidokarma.presentation.state.FeedbackState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class FeedbackViewModel(
    private val insertFeedbackUseCase: InsertFeedbackUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FeedbackState())
    val state: StateFlow<FeedbackState> = _state.asStateFlow()

    fun onEvent(event: FeedbackEvent) {
        when (event) {
            is FeedbackEvent.EmojiChanged -> {
                _state.update { it.copy(emoji = event.emoji) }
            }
            is FeedbackEvent.TagChanged -> {
                _state.update { it.copy(tag = event.tag) }
            }
            is FeedbackEvent.CommentChanged -> {
                _state.update { it.copy(comment = event.comment) }
            }
            FeedbackEvent.SubmitFeedback -> {
                submitFeedback()
            }

            is FeedbackEvent.TipAmountChanged -> {}
        }
    }

    private fun submitFeedback() {
        viewModelScope.launch {
            try {
                _state.update { it.copy(isSubmitting = true, isSuccess = false, errorMessage = null) }

                val feedback = Feedback(
                    emoji = _state.value.emoji,
                    tag = _state.value.tag,
                    comment = _state.value.comment,
                    timestamp = System.currentTimeMillis()
                )

                insertFeedbackUseCase(feedback)

                _state.update {
                    it.copy(
                        isSubmitting = false,
                        isSuccess = true,
                        emoji = "",
                        tag = "",
                        comment = ""
                    )
                }
            } catch (e: Exception) {
                _state.update { it.copy(isSubmitting = false, errorMessage = e.message ?: "Unknown error") }
            }
        }
    }
}

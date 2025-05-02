package com.example.rapidokarma.presentation.event

sealed class FeedbackEvent {
    data class EmojiChanged(val emoji: String) : FeedbackEvent()
    data class TagChanged(val tag: String) : FeedbackEvent()
    data class CommentChanged(val comment: String) : FeedbackEvent()
    data class TipAmountChanged(val amount: Int) : FeedbackEvent()
    object SubmitFeedback : FeedbackEvent()
}
package com.example.rapidokarma.presentation.state

data class FeedbackState(
    val emoji: String = "",
    val tag: String = "",
    val comment: String = "",
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

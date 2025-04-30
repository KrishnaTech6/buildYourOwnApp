package com.example.rapidokarma.domain.usecase

import com.example.rapidokarma.domain.model.Feedback
import com.example.rapidokarma.domain.repository.FeedbackRepository

class InsertFeedbackUseCase(
    private val repository: FeedbackRepository
) {
    suspend operator fun invoke(feedback: Feedback) {
        repository.insertFeedback(feedback)
    }
}

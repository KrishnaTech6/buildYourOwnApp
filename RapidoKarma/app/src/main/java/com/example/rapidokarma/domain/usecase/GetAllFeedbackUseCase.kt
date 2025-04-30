package com.example.rapidokarma.domain.usecase

import com.example.rapidokarma.domain.model.Feedback
import com.example.rapidokarma.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.Flow

class GetAllFeedbackUseCase(
    private val repository: FeedbackRepository
) {
    operator fun invoke(): Flow<List<Feedback>> {
        return repository.getAllFeedback()
    }
}

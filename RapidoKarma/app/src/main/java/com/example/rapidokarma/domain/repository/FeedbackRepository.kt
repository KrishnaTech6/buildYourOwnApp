package com.example.rapidokarma.domain.repository

import com.example.rapidokarma.domain.model.Feedback
import kotlinx.coroutines.flow.Flow

interface FeedbackRepository {
    suspend fun insertFeedback(feedback: Feedback)
    fun getAllFeedback(): Flow<List<Feedback>>
}

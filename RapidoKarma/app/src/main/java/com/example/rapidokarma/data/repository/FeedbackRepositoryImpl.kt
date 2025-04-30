package com.example.rapidokarma.data.repository

import com.example.rapidokarma.data.local.dao.FeedbackDao
import com.example.rapidokarma.data.local.mapper.toDomain
import com.example.rapidokarma.data.local.mapper.toEntity
import com.example.rapidokarma.domain.model.Feedback
import com.example.rapidokarma.domain.repository.FeedbackRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeedbackRepositoryImpl(
    private val dao: FeedbackDao
) : FeedbackRepository {
    override suspend fun insertFeedback(feedback: Feedback) {
        dao.insertFeedback(feedback.toEntity())
    }

    override fun getAllFeedback(): Flow<List<Feedback>> {
        return dao.getAllFeedback().map { feedbackList ->
            feedbackList.map { it.toDomain() }
        }
    }

}

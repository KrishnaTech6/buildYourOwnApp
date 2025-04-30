package com.example.rapidokarma.data.local.mapper

import com.example.rapidokarma.data.local.entity.FeedbackEntity
import com.example.rapidokarma.domain.model.Feedback

fun FeedbackEntity.toDomain(): Feedback {
    return Feedback(
        id = id,
        emoji = emoji,
        tag = tag,
        comment = comment,
        timestamp = timestamp
    )
}

fun Feedback.toEntity(): FeedbackEntity {
    return FeedbackEntity(
        id = id,
        emoji = emoji,
        tag = tag,
        comment = comment,
        timestamp = timestamp
    )
}

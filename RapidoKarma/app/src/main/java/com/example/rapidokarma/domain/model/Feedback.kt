package com.example.rapidokarma.domain.model

data class Feedback(
    val id: Long = 0L,
    val emoji: String,
    val tag: String,
    val comment: String,
    val timestamp: Long
)

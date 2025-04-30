package com.example.rapidokarma.domain.model

data class Feedback(
    val id: Int = 0,
    val emoji: String,
    val tag: String,
    val comment: String,
    val timestamp: Long
)

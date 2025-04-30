package com.example.rapidokarma.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rapidokarma.data.local.dao.FeedbackDao
import com.example.rapidokarma.data.local.entity.FeedbackEntity

@Database(
    entities = [FeedbackEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun feedbackDao(): FeedbackDao

    companion object {
        const val DB_NAME = "feedback_db"
    }
}

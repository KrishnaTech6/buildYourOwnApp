package com.example.rapidokarma.di

import androidx.room.Room
import com.example.rapidokarma.data.local.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().feedbackDao() }
}

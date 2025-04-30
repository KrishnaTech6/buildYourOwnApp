package com.example.rapidokarma.di

import com.example.rapidokarma.data.repository.FeedbackRepositoryImpl
import com.example.rapidokarma.domain.repository.FeedbackRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<FeedbackRepository> { FeedbackRepositoryImpl(get()) }
}

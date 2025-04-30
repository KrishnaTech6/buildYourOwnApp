package com.example.rapidokarma.di

import com.example.rapidokarma.domain.usecase.GetAllFeedbackUseCase
import com.example.rapidokarma.domain.usecase.InsertFeedbackUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory { InsertFeedbackUseCase(get()) }
    factory { GetAllFeedbackUseCase(get()) }
}

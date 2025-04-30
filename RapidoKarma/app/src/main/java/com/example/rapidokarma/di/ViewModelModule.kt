package com.example.rapidokarma.di

import com.example.rapidokarma.presentation.viewmodel.FeedbackViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        FeedbackViewModel(insertFeedbackUseCase = get())
    }
}
# Driver Feedback App

A modern Android application that enables users to submit feedback for drivers in an offline-first design. Built to demonstrate clean architecture, modern Android tools, and robust offline persistence using Room.

## Features

- Submit feedback for a driver, including text and rating
- Sentiment tagging (Positive, Negative, Neutral) based on content
- Offline-first support using Room database
- Feedback history screen with all stored entries
- Swipe-to-delete with Undo via Snackbar
- Thank You screen with Lottie animation for better UX
- Built with Jetpack Compose and Material Design 3

## Architecture

This project follows **Clean Architecture** with the following structure:

- **Data Layer**: Room database, DAO, and repository implementations
- **Domain Layer**: Use cases encapsulating business logic
- **Presentation Layer**: Jetpack Compose UI, ViewModels, and state management using MVI

### Tech Stack

- **Language**: Kotlin  
- **UI**: Jetpack Compose  
- **Architecture**: Clean Architecture + MVI  
- **Async**: Kotlin Coroutines and Flow  
- **Persistence**: Room  
- **Dependency Injection**: Hilt  
- **Navigation**: Navigation Compose  
- **Testing**: JUnit 5 (with ViewModel and UseCase unit tests)  
- **Other Libraries**: Lottie for animations  

## How to Run

1. Clone the repository  
2. Open in Android Studio  
3. Build and run the project on a device or emulator (API 26+)

## Limitations

- Driver list is hardcoded (can be fetched from backend in future)
- No real-time sync to cloud/server
- Sentiment detection is basic keyword-based, not ML-driven

## Possible Improvements

- Firebase sync for feedback across devices
- Voice-to-text input for feedback using SpeechRecognizer
- Advanced NLP-based sentiment analysis
- Realtime feedback notifications for drivers via Firebase Messaging

## Project Status

Complete and fully functional as per the assignment requirements. Designed to be extended with minimal changes.

# Why this App?

While ride-sharing platforms provide a way to rate drivers, the actual feedback often gets lost in aggregated ratings. Riders may want to share more meaningful input—especially when something goes notably wrong or right—but don't get a proper medium for it. At the same time, drivers rarely receive constructive feedback that could help them improve or feel motivated.

This app aims to solve that gap by allowing riders to submit detailed, sentiment-tagged feedback for their drivers in a lightweight, offline-first experience. It helps capture feedback even in low-network conditions and can later sync with a backend (in future iterations). The design encourages honesty, empathy, and actionable input for driver performance and experience.

---

# Driver Feedback App

A modern Android application that enables users to submit feedback for drivers in an offline-first design. Built to demonstrate clean architecture, modern Android tools, and robust offline persistence using Room.

## Features

- Submit feedback for a driver, including text and rating
- User can tip the driver
- Offline-first support using Room database
- Thank You screen with Confetti animation for better UX
- Built with Jetpack Compose and Material Design 3

## Architecture

This project follows **Clean Architecture** with the following structure:

- **Data Layer**: Room database, DAO, and repository implementations
- **Domain Layer**: Use cases encapsulating business logic
- **Presentation Layer**: Jetpack Compose UI, ViewModels, and state management using MVI

### Screen Recording
https://drive.google.com/file/d/1FNXmeKktvRY9IvBmmEIYLHcw33CyH5uN/view?usp=sharing

### Download the APK
https://drive.google.com/file/d/1-jnmAULSLxxwBThValv70xZaFFWYZICD/view?usp=sharing

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

package com.example.rapidokarma.presentation.state

data class FeedbackState(
    val driverName: String = "Rahul",  // Example name
    val driverPhoto: String = "https://i.sstatic.net/R121o.png?s=256",  // URL or resource ID
    val rideId: String = "RID123456",
    val pickupLocation: String = "Vaishali",
    val dropLocation: String = "Janakpuri",
    val emoji: String = "",
    val tag: String = "",
    val comment: String = "",
    val tipAmount: Int = 0,
    val isSubmitting: Boolean = false,
    val isSuccess: Boolean = false,
    val errorMessage: String? = null
)

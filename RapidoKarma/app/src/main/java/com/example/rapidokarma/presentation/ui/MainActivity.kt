package com.example.rapidokarma.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rapidokarma.presentation.ui.theme.RapidoKarmaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RapidoKarmaTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "feedback"
                ) {
                    composable("feedback") {
                        FeedbackRoute()
                    }
                }
            }
        }
    }
}
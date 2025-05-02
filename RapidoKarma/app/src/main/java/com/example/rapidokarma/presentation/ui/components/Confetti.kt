package com.example.rapidokarma.presentation.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlinx.coroutines.launch
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

@Composable
fun Confetti(
    modifier: Modifier = Modifier,
    pieces: Int = 50
) {
    val particles = remember {
        List(pieces) {
            Particle(
                x = Random.nextFloat() * 1000,
                y = -Random.nextFloat() * 1000,
                rotation = Random.nextFloat() * 360f,
                color = listOf(
                    Color(0xFFE91E63),
                    Color(0xFF2196F3),
                    Color(0xFF4CAF50),
                    Color(0xFFFFC107),
                    Color(0xFF9C27B0)
                ).random()
            )
        }
    }

    val animatedProgress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        animatedProgress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 3000,
                easing = LinearEasing
            )
        )
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        particles.forEach { particle ->
            rotate(particle.rotation + (animatedProgress.value * 360f)) {
                drawRect(
                    color = particle.color,
                    topLeft = Offset(
                        x = particle.x + (cos(animatedProgress.value * 2 * Math.PI.toFloat()) * 100f),
                        y = particle.y + (animatedProgress.value * size.height * 1.5f)
                    ),
                    size = size / 40f
                )
            }
        }
    }
}

private data class Particle(
    val x: Float,
    val y: Float,
    val rotation: Float,
    val color: Color
)

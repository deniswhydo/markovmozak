package com.markovmozak.app.ui.components

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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import kotlin.math.sin
import kotlin.random.Random

private data class ConfettiPiece(
    val x: Float,
    val speed: Float,
    val amplitude: Float,
    val phase: Float,
    val size: Float,
    val color: Color,
    val rotation: Float
)

private val confettiColors = listOf(
    Color(0xFFFF6B35),
    Color(0xFF004E89),
    Color(0xFF4CAF50),
    Color(0xFFE91E63),
    Color(0xFFFFC107),
    Color(0xFF9C27B0),
    Color(0xFF00BCD4),
    Color(0xFFFF5722)
)

@Composable
fun ConfettiAnimation(
    trigger: Boolean,
    key: Any? = null,
    modifier: Modifier = Modifier
) {
    if (!trigger) return

    val progress = remember(key) { Animatable(0f) }
    val pieces = remember(key) {
        List(50) {
            ConfettiPiece(
                x = Random.nextFloat(),
                speed = 0.5f + Random.nextFloat() * 0.5f,
                amplitude = 0.02f + Random.nextFloat() * 0.04f,
                phase = Random.nextFloat() * 6.28f,
                size = 6f + Random.nextFloat() * 10f,
                color = confettiColors.random(),
                rotation = Random.nextFloat() * 360f
            )
        }
    }

    LaunchedEffect(key) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 2000, easing = LinearEasing)
        )
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val w = size.width
        val h = size.height
        val p = progress.value

        pieces.forEach { piece ->
            val yProgress = p * piece.speed
            if (yProgress > 1f) return@forEach

            val baseX = piece.x * w
            val wobble = sin((p * 10f + piece.phase).toDouble()).toFloat() * piece.amplitude * w
            val x = baseX + wobble
            val y = yProgress * h * 1.2f

            rotate(degrees = piece.rotation + p * 360f * piece.speed, pivot = Offset(x, y)) {
                drawRect(
                    color = piece.color.copy(alpha = 1f - yProgress),
                    topLeft = Offset(x - piece.size / 2, y - piece.size / 2),
                    size = Size(piece.size, piece.size * 0.6f)
                )
            }
        }
    }
}

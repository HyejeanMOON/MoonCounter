package com.hyejeanmoon.mooncounter.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun LeftTimeAnimationView(
    modifier: Modifier = Modifier,
    leftTime: Int
) {
    val infiniteTransition = rememberInfiniteTransition()
    val factor by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = tween(leftTime * 1000, easing = LinearEasing)
        )
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        val size = Size(radius * 2, radius * 2)

        drawArc(
            brush = Brush.radialGradient(
                radius = radius,
                colors = listOf(
                    Color.Cyan,
                    Color.Green,
                    Color.White
                )
            ),
            startAngle = 0F,
            sweepAngle = factor * 360F,
            useCenter = true,
            alpha = 0.2F,
            style = Fill
        )
    }
}

@Composable
fun SecondTimeAnimationView(
    modifier: Modifier = Modifier
) {

    val infiniteTransition = rememberInfiniteTransition()
    val factor by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 1F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val diameter = size.minDimension
        val radius = diameter / 2f
        drawCircle(
            color = Color.Black,
            radius = radius,
            style = Stroke(5F),
            alpha = factor
        )
    }
}

@Preview
@Composable
fun PreviewSecondAnimationView() {
    Box(modifier = Modifier.size(220.dp,220.dp)){
        LeftTimeAnimationView(
            modifier = Modifier.size(200.dp, 200.dp),
            leftTime = 5
        )
        SecondTimeAnimationView(
            modifier = Modifier.size(210.dp,210.dp)
        )

    }
}
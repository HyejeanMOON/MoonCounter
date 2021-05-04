package com.hyejeanmoon.mooncounter.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun LeftTimeTextField(
    modifier: Modifier = Modifier,
    leftTime: Int
) {
    var time by remember {
        mutableStateOf(leftTime)
    }

    DisposableEffect(time) {
        onDispose {
            time = 0
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val factor by infiniteTransition.animateFloat(
        initialValue = time.toFloat(),
        targetValue = 0F,
        animationSpec = infiniteRepeatable(
            animation = tween(time * 1000, easing = LinearEasing)
        )
    )

    val scaleFactor by infiniteTransition.animateFloat(
        initialValue = 1F,
        targetValue = 1.5F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val millisecondFactor by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 100F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing)
        )
    )

    val hours = (factor / 3600).toInt()
    val minutes = (factor / 60 - hours * 60).toInt()
    val seconds = (factor % 60).toInt()

    val hourText = if (hours < 10) "0$hours" else hours
    val minuteText = if (minutes < 10) "0$minutes" else minutes
    val secondText = if (seconds < 10) "0$seconds" else seconds
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$hourText : $minuteText : $secondText",
            modifier = Modifier,
            color = Color.Black,
            fontSize = if(seconds<10)40.sp * scaleFactor else 40.sp
        )
        Text(
            text = if (millisecondFactor < 10F) ".0${millisecondFactor.toInt()}" else ".${millisecondFactor.toInt()}",
            modifier = Modifier,
            color = Color.Black,
            fontSize = if(seconds<10)32.sp * scaleFactor else 32.sp
        )

    }
}

@Composable
fun SettingDevidingIcon(
    modifier: Modifier = Modifier
) {
    Text(
        text = ":",
        modifier = modifier,
        fontSize = 30.sp,
        color = Color.Black
    )
}

@Preview
@Composable
fun PreviewLeftTimeTextField() {
    Column {
        LeftTimeTextField(leftTime = 78)
        SettingDevidingIcon()
    }
}
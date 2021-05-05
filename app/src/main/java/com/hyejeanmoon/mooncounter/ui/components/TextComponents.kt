package com.hyejeanmoon.mooncounter.ui.components

import android.util.Log
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
    settingTime: Int
) {
    var leftTimeState by remember {
        mutableStateOf(0)
    }

    val elapsedTime by animateIntAsState(
        targetValue = leftTimeState,
        animationSpec = tween(
            durationMillis = leftTimeState * 1000,
            easing = LinearEasing
        )
    )

    DisposableEffect(Unit) {
        leftTimeState = settingTime
        onDispose {  }
    }

    val infiniteTransition = rememberInfiniteTransition()

    val scaleFactor by infiniteTransition.animateFloat(
        initialValue = 1F,
        targetValue = 1.5F,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val leftTime = settingTime - elapsedTime

    val hours = leftTime / 3600
    val minutes = leftTime / 60 - hours * 60
    val seconds = leftTime % 60

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
            fontSize = if(hours == 0 && minutes == 0 && seconds<10)40.sp * scaleFactor else 40.sp
        )
        millisecondsText(settingTime = leftTime)
    }
}

@Composable
fun millisecondsText(
    modifier: Modifier = Modifier,
    settingTime: Int
){
    var leftTimeState by remember {
        mutableStateOf(0)
    }

    val elapsedTime by animateIntAsState(
        targetValue = leftTimeState,
        animationSpec = tween(
            durationMillis = leftTimeState,
            easing = LinearEasing
        )
    )

    DisposableEffect(Unit) {
        leftTimeState = settingTime * 1000
        onDispose {  }
    }

    Text(
        text = if ((leftTimeState - elapsedTime) % 100 < 10F) ".0${(leftTimeState - elapsedTime)% 100}" else ".${(leftTimeState - elapsedTime)% 100}",
        modifier = modifier,
        color = Color.Black,
        fontSize =  32.sp
    )
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
        LeftTimeTextField(settingTime = 78)
        SettingDevidingIcon()
    }
}
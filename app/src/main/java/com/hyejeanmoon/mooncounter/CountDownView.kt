package com.hyejeanmoon.mooncounter

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hyejeanmoon.mooncounter.ui.components.LeftTimeAnimationView
import com.hyejeanmoon.mooncounter.ui.components.LeftTimeTextField
import com.hyejeanmoon.mooncounter.ui.components.StopButton

@Composable
fun CountDownView(
    modifier: Modifier = Modifier,
    timeInSeconds: Int,
    onStop: () -> Unit
) {
    val seconds by remember { mutableStateOf(timeInSeconds) }

    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        LeftTimeAnimationView(
            settingTime = seconds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LeftTimeTextField(settingTime = seconds)

            StopButton(
                modifier = Modifier.padding(0.dp, 30.dp, 0.dp, 0.dp)
            ) {
                onStop()
            }
        }
    }
}

@Preview
@Composable
fun PreviewCountDownView() {
    CountDownView(timeInSeconds = 10, onStop = {})
}
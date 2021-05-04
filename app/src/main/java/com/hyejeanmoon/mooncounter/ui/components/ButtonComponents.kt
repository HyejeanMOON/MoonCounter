package com.hyejeanmoon.mooncounter.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun StartButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = false
) {
    Button(
        onClick = onClick,
        modifier = modifier.size(80.dp,45.dp),
        enabled = isEnabled
    ) {
        Text(text = "Start")
    }
}

@Composable
fun StopButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Button(
        onClick = onClick,
        modifier = modifier.size(80.dp,45.dp)
    ) {
        Text(text = "Stop")
    }
}

@Preview
@Composable
fun PreviewButton() {
    Column {
        StartButton(onClick = {})
        StopButton(onClick = {})
    }
}
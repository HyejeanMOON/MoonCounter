package com.hyejeanmoon.mooncounter.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hyejeanmoon.mooncounter.R
import com.hyejeanmoon.mooncounter.ui.components.SettingDevidingIcon
import com.hyejeanmoon.mooncounter.ui.components.StartButton
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

@Composable
fun TimeSettingView(
    modifier: Modifier = Modifier,
    onCount:(Int) -> Unit
) {
    var hour by remember { mutableStateOf(0) }
    var minute by remember { mutableStateOf(0) }
    var second by remember { mutableStateOf(0) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            // Set Hours
            SetLeftTime(
                time = hour,
                onClickForUp = {
                    if (it < 11) {
                        hour = it + 1
                    }
                },
                onClickForDown = {
                    if (it > 0) {
                        hour = it - 1
                    }
                },
                isHour = true
            )

            SettingDevidingIcon(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .align(Alignment.CenterVertically)
            )

            // Set Minutes
            SetLeftTime(
                time = minute,
                onClickForUp = {
                    if (it < 59) {
                        minute = it + 1
                    }
                },
                onClickForDown = {
                    if (it > 0) {
                        minute = it - 1
                    }
                }
            )

            SettingDevidingIcon(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .align(Alignment.CenterVertically)
            )

            // Set Seconds
            SetLeftTime(
                time = second,
                onClickForUp = {
                    if (it < 59) {
                        second = it + 1
                    }
                },
                onClickForDown = {
                    if (it > 0) {
                        second = it - 1
                    }
                }
            )
        }

        StartButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(0.dp, 30.dp),
            onClick = {
                Log.d("MOON", "hour: $hour minute: $minute second: $second")
                onCount(hour*3600 + minute*60 + second)
            },
            isEnabled = !(hour == 0 && minute == 0 && second == 0)
        )
    }
}

@Composable
fun SetLeftTime(
    modifier: Modifier = Modifier,
    time: Int,
    onClickForUp: (Int) -> Unit,
    onClickForDown: (Int) -> Unit,
    isHour: Boolean = false
) {

    val upIcon =
        if (time >= 59 || (isHour && time >= 11)) R.drawable.ic_up_gray else R.drawable.ic_up
    val downIcon = if (time <= 0) R.drawable.ic_down_gray else R.drawable.ic_down

    Column(modifier = modifier) {
        Image(
            imageVector = ImageVector.vectorResource(upIcon),
            contentDescription = "up",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .size(50.dp, 50.dp)
                .clickable { onClickForUp(time) }
        )

        val hourText = if (time < 10) "0$time" else time.toString()
        Text(
            text = hourText,
            modifier = modifier,
            fontSize = 50.sp,
            color = Color.Black
        )

        Image(
            imageVector = ImageVector.vectorResource(downIcon),
            contentDescription = "down",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .size(50.dp, 50.dp)
                .clickable { onClickForDown(time) }
        )
    }
}

@Preview
@Composable
fun PreviewSetLeftTime() {
    SetLeftTime(
        time = 1,
        onClickForUp = {},
        onClickForDown = {}
    )
}

@Preview
@Composable
fun PreviewTimeSettingView() {
    TimeSettingView(onCount = {})
}
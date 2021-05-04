package com.hyejeanmoon.mooncounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.hyejeanmoon.mooncounter.ui.TimeSettingView
import com.hyejeanmoon.mooncounter.ui.theme.MoonCounterTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoonCounterTheme {
                // A surface container using the 'background' color from the theme
                CounterApp()
            }
        }
    }

    override fun onBackPressed() {
        // do nothing
    }
}

@Composable
fun CounterApp(){
    Surface(color = MaterialTheme.colors.background) {
        var screen by remember { mutableStateOf(Screen.SettingScreen) }
        var seconds by remember {
            mutableStateOf(0)
        }

        Crossfade(targetState = screen) {
            when (it) {
                Screen.SettingScreen -> TimeSettingView(onCount = {
                    seconds = it
                    screen = Screen.CountDownScreen
                })
                Screen.CountDownScreen -> CountDownView(
                    timeInSeconds = seconds,
                    onStop = {
                        screen = Screen.SettingScreen
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MoonCounterTheme {
        CounterApp()
    }
}

enum class Screen {
    SettingScreen, CountDownScreen
}
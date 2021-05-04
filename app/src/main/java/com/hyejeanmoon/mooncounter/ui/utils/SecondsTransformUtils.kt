package com.hyejeanmoon.mooncounter.ui.utils

object SecondsTransformUtils {
    fun transformToSeconds(
        hours: Int,
        minutes: Int,
        seconds: Int
    ): Int {
        return hours * 3600 + minutes * 60 + seconds
    }
}
package com.example.si_mobile_demo.ui.theme

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing

object PrimitiveMotion {

    // Durations (ms)
    const val SHORT = 100
    const val MEDIUM = 200
    const val LONG = 300
    const val EMPHASIS = 400
    const val SLOW = 500
    const val VERY_SLOW = 1200

    val EaseStandard = LinearEasing
    val EaseDecelerate = LinearOutSlowInEasing
    val EaseAccelerate = FastOutLinearInEasing
}
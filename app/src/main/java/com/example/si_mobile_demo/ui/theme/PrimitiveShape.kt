package com.example.si_mobile_demo.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

object PrimitiveShape {
    val None = RoundedCornerShape(0.dp)
    val Xs = RoundedCornerShape(2.dp)    // RadiusXS
    val Sm = RoundedCornerShape(4.dp)    // RadiusSmall
    val Md = RoundedCornerShape(8.dp)    // RadiusMedium
    val Lg = RoundedCornerShape(16.dp)   // RadiusLarge
    val Xl = RoundedCornerShape(24.dp)   // RadiusXL
    val Xxl = RoundedCornerShape(32.dp)  // RadiusXXL
    val Xxxl = RoundedCornerShape(50.dp) // RadiusXXXL
    val Full = RoundedCornerShape(50)    // RadiusFull (percent)
}
package com.example.si_mobile_demo.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class SiTypographyTokens(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val body1: TextStyle,
    val body1Bold: TextStyle,
    val body2: TextStyle,
    val body2Bold: TextStyle,
    val caption1: TextStyle,
    val caption1Bold: TextStyle,
    val caption2: TextStyle,
    val caption2Bold: TextStyle,
    val label: TextStyle,
    val buttonGiant: TextStyle,
    val buttonLarge: TextStyle,
    val buttonMedium: TextStyle,
    val buttonSmall: TextStyle,
    val buttonTiny: TextStyle,
)

// Factory function — @Composable needed for BeVietnamPro Google Font resolution
@Composable
fun defaultSiTypography(): SiTypographyTokens = SiTypographyTokens(
    h1 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 48.sp),
    h2 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 40.sp),
    h3 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 32.sp),
    h4 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
    h5 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
    subtitle1 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 18.sp),
    subtitle2 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 16.sp),
    body1 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Normal, fontSize = 16.sp),
    body1Bold = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    body2 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Normal, fontSize = 14.sp),
    body2Bold = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
    caption1 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Medium, fontSize = 12.sp),
    caption1Bold = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 12.sp),
    caption2 = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Medium, fontSize = 10.sp),
    caption2Bold = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.Bold, fontSize = 10.sp),
    label = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
    buttonGiant = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
    buttonLarge = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
    buttonMedium = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
    buttonSmall = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
    buttonTiny = TextStyle(fontFamily = BeVietnamPro, fontWeight = FontWeight.SemiBold, fontSize = 10.sp),
)

// Fallback without font family (will be overridden by SiTheme provider)
val LocalSiTypography = staticCompositionLocalOf {
    SiTypographyTokens(
        h1 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 48.sp),
        h2 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 40.sp),
        h3 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 32.sp),
        h4 = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 28.sp),
        h5 = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 24.sp),
        subtitle1 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
        subtitle2 = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
        body1 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 16.sp),
        body1Bold = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
        body2 = TextStyle(fontWeight = FontWeight.Normal, fontSize = 14.sp),
        body2Bold = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
        caption1 = TextStyle(fontWeight = FontWeight.Medium, fontSize = 12.sp),
        caption1Bold = TextStyle(fontWeight = FontWeight.Bold, fontSize = 12.sp),
        caption2 = TextStyle(fontWeight = FontWeight.Medium, fontSize = 10.sp),
        caption2Bold = TextStyle(fontWeight = FontWeight.Bold, fontSize = 10.sp),
        label = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
        buttonGiant = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp),
        buttonLarge = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 16.sp),
        buttonMedium = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 14.sp),
        buttonSmall = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 12.sp),
        buttonTiny = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 10.sp),
    )
}

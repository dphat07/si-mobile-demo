package com.example.si_mobile_demo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.example.si_mobile_demo.R

val BeVietnamPro = FontFamily(
    Font(
        googleFont = GoogleFont("Be Vietnam Pro"),
        fontProvider = GoogleFont.Provider(
            providerAuthority = "com.google.android.gms.fonts",
            providerPackage = "com.google.android.gms",
            certificates = R.array.com_google_android_gms_fonts_certs
        )
    )
)

private val typoBaseline = Typography()

val AppTypography = typoBaseline.copy(
    displayLarge = typoBaseline.displayLarge.copy(fontFamily = BeVietnamPro),
    displayMedium = typoBaseline.displayMedium.copy(fontFamily = BeVietnamPro),
    displaySmall = typoBaseline.displaySmall.copy(fontFamily = BeVietnamPro),
    headlineLarge = typoBaseline.headlineLarge.copy(fontFamily = BeVietnamPro),
    headlineMedium = typoBaseline.headlineMedium.copy(fontFamily = BeVietnamPro),
    headlineSmall = typoBaseline.headlineSmall.copy(fontFamily = BeVietnamPro),
    titleLarge = typoBaseline.titleLarge.copy(fontFamily = BeVietnamPro),
    titleMedium = typoBaseline.titleMedium.copy(fontFamily = BeVietnamPro),
    titleSmall = typoBaseline.titleSmall.copy(fontFamily = BeVietnamPro),
    bodyLarge = typoBaseline.bodyLarge.copy(fontFamily = BeVietnamPro),
    bodyMedium = typoBaseline.bodyMedium.copy(fontFamily = BeVietnamPro),
    bodySmall = typoBaseline.bodySmall.copy(fontFamily = BeVietnamPro),
    labelLarge = typoBaseline.labelLarge.copy(fontFamily = BeVietnamPro),
    labelMedium = typoBaseline.labelMedium.copy(fontFamily = BeVietnamPro),
    labelSmall = typoBaseline.labelSmall.copy(fontFamily = BeVietnamPro),
)

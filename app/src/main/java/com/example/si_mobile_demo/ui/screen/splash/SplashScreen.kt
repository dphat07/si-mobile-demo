package com.example.si_mobile_demo.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.example.si_mobile_demo.R

@Composable
fun SplashScreen(onNavigateToLogin: () -> Unit) {
    val splashComposition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_aim))
    val splashProgress by animateLottieCompositionAsState(
        composition = splashComposition,
        iterations = 1
    )

    val gradient = Brush.verticalGradient(listOf(Color.White,Color.White, Color(0xFF60ECFF)))

    LaunchedEffect(splashProgress) {
        if (splashProgress == 1f)
            onNavigateToLogin()
    }

    Box(modifier = Modifier.fillMaxSize().background(gradient), contentAlignment = Alignment.Center) {
        LottieAnimation(
            composition = splashComposition,
            progress = { splashProgress },
            modifier = Modifier.size(250.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreen(onNavigateToLogin = {})
}
package com.example.si_mobile_demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.si_mobile_demo.ui.screen.login.LoginScreen
import com.example.si_mobile_demo.ui.screen.splash.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController,
               modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashRouteData,
        modifier = modifier
    ) {
        composable<SplashRouteData> {
            SplashScreen(onNavigateToLogin = {
                navController.navigate(LoginRouteData){
                    popUpTo(SplashRouteData){
                        inclusive = true
                    }
                }
            })
        }

        composable<LoginRouteData> {
            LoginScreen()
        }
    }
}
package com.example.si_mobile_demo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.si_mobile_demo.ui.screen.login.LoginScreen
import com.example.si_mobile_demo.ui.screen.select_class.SelectClassScreen
import com.example.si_mobile_demo.ui.screen.splash.SplashScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute,
        modifier = modifier
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginRoute) {
                        popUpTo<SplashRoute> { inclusive = true }
                    }
                }
            )
        }

        composable<LoginRoute> {
            LoginScreen(navController = navController)
        }

        composable<SelectClassRoute> {
            SelectClassScreen(navController = navController)
        }

        composable<MainRoute> {
            MainNavGraph()
        }
    }
}

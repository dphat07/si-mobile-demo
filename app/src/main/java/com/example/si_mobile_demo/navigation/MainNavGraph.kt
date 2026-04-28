package com.example.si_mobile_demo.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun MainNavGraph() {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            SiBottomNavigation(
                currentDestination = currentDestination,
                onItemClick = { route: AppRoute ->
                    navController.navigate(route) {
                        popUpTo<SearchRoute> {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = SearchRoute,
            modifier = Modifier.padding(padding)
        ) {
            composable<SearchRoute> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Search Screen")
                }
            }
            composable<AttendanceRoute> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Attendance")
                }
            }
            composable<ScoreRoute> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Score")
                }
            }
            composable<ProfileRoute> {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Profile")
                }
            }
        }
    }
}

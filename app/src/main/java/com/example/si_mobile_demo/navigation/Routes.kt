package com.example.si_mobile_demo.navigation

import kotlinx.serialization.Serializable

sealed interface AppRoute

@Serializable data object SplashRoute : AppRoute

@Serializable data object LoginRoute : AppRoute

@Serializable data object MainRoute : AppRoute

@Serializable data object SearchRoute : AppRoute

@Serializable data object AttendanceRoute : AppRoute

@Serializable data object ScoreRoute : AppRoute

@Serializable data object ProfileRoute : AppRoute
